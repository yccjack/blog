package com.mystic.ycc.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

@SpringBootApplication
@MapperScan("com.mystic.ycc.blog.dao")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}


@Data
class RpcResponse<T> {
    private Boolean success;
    private T data;
    // 分⻚令牌，如果返回值不为空，则通过该令牌能够执⾏下⼀次查询
    private byte[] token;
}

@Data
abstract class BaseMetericQuery {
    private byte[] token;
}

class MetricXxxQuery extends BaseMetericQuery {
// 查询字段
}

class MetricYyyQuery extends BaseMetericQuery {
// 查询字段
}

class MetricZzzQuery extends BaseMetericQuery {
// 查询字段
}

class MetricQueryPager {
    /**
     * @param queryFunction 查询函数：【RpcResponse<List<MetricXxxDTO> listMetrics(MetricXxxQuery query);
     *                      RpcResponse<List<MetricYyyDTO> listMetrics(MetricYyyQuery query);
     *                      RpcResponse<List<MetricZzzDTO> listMetrics(MetricZzzQuery query);】
     * @param initialQuery  查询参数 【MetricXxxQuery，MetricYyyQuery，MetricZzzQuery】
     * @param <T>           {@link BaseMetericQuery}
     * @param <R>           {@link RpcResponse}
     * @param <D>           【MetricYyyDTO，MetricYyyDTO，MetricZzzDTO】
     * @return 查询结果 {@link RpcResponse}
     */
    public static <T extends BaseMetericQuery, R extends RpcResponse<List<D>>, D> List<D> autoPaged(
            Function<T, R> queryFunction, T initialQuery) {
        List<D> allData = new ArrayList<>();
        T currentQuery = initialQuery;
        byte[] token = null;
        //重试次数
        int count = 0;
        R response = queryFunction.apply(currentQuery);
        do {
            if (response.getSuccess() != null && response.getSuccess()) {
                List<D> data = response.getData();
                if (data != null) {
                    allData.addAll(data);
                }
                token = response.getToken();
                //token为空说明查询结束
                if (token == null || token.length == 0) {
                    break;
                }
                currentQuery.setToken(token);
            } else {
                //假设返回失败后不会再返回token，则直接判断重试次数是否大于3,大于直接结束;
                if (count < 3) {
                    count++;
                } else {
                    break;
                }
            }
            response = queryFunction.apply(currentQuery);
            //防止长时间阻塞
            Thread.yield();
        } while (token != null);

        return allData;
    }
}


/**
 * 充电宝的充放指令
 */
@Data
@AllArgsConstructor
class DispatchCommand {
    private LocalTime startTime;
    private LocalTime endTime;
    // 正数表⽰充电，负数表⽰放电
    private Double power;

    @Override
    public String toString() {
        return "DispatchCommand{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", power=" + power +
                '}';
    }
}

/**
 * 设备参数
 */
record DeviceSpecDTO(Double capacity, Double standardPower) {
}

/**
 * 充放调度的上下⽂
 */
record DispatchContext(TouDTO touDTO, DeviceSpecDTO deviceSpecDTO) {
}

/**
 * 峰⾕时段配置
 */
record TouPeriodDTO(TouState state, LocalTime startTime, LocalTime endTime) {
}

/**
 * 峰⾕电价配置
 */
record TouPriceDTO(TouState state, Double price) {
}

/**
 * 单⽇峰⾕时段与电价配置
 */
record TouDTO(LocalDate date, Set<TouPeriodDTO> periods, Set<TouPriceDTO>
        prices) {
}

// 定义时段状态的枚举
enum TouState {
    OFF_PEAK, // ⾕
    SHOULDER, // 平
    PEAK,     // 峰
    CRITICAL_PEAK // 尖
}

class BatteryDispatch {

    /**
     *
     * @param context {@link DispatchContext}
     * @return {@link DispatchCommand}
     */
    public static List<DispatchCommand> dispatch(DispatchContext context) {
        TouDTO touDTO = context.touDTO();
        DeviceSpecDTO deviceSpec = context.deviceSpecDTO();
        //暂不考虑
        double capacity = deviceSpec.capacity();
        double standardPower = deviceSpec.standardPower();

        List<TouPeriodDTO> periods = new ArrayList<>(touDTO.periods());
        List<TouPriceDTO> prices = new ArrayList<>(touDTO.prices());

        // 按照电价从低到高排序
        prices.sort(Comparator.comparing(TouPriceDTO::price));
        periods.sort(Comparator.comparing(TouPeriodDTO::startTime));

        List<DispatchCommand> commands = new ArrayList<>();
        double currentEnergy = 0.0;
        int ordinalPre = 0;
        for (int i = 0; i < periods.size(); i++) {
            TouPeriodDTO touPeriodDTO = periods.get(i);
            TouState state = touPeriodDTO.state();
            LocalTime start = touPeriodDTO.startTime();
            LocalTime end = touPeriodDTO.endTime();
            int ordinal = state.ordinal();
            //未充电状态
            if (currentEnergy <= 0.0) {
                //尖时不充电
                if(state== TouState.CRITICAL_PEAK){
                    continue;
                }
                //不创建充电实例
                int canIn=0;
                //记录当前的枚举的排序值
                ordinalPre=ordinal;
                //检索是否有尖时放电机会；
                for (int j = i+1; j < periods.size(); j++) {
                    TouPeriodDTO touPeriodDTOJ = periods.get(j);
                    TouState stateJ = touPeriodDTOJ.state();
                    int ordinalJ = stateJ.ordinal();
                    //如果之后有记录的枚举大于当前的说明可以充电，后续可以放电
                    if (ordinalJ > ordinal) {
                        canIn=1;
                        //记录当前的枚举排序值，继续查询是否含有尖时放电机会
                        ordinal=ordinalJ;
                    }

                    //如果存在记录大于当前的枚举值后又存在小于当前枚举值的，则存在下一次的冲放电机会
                    if(ordinalJ<ordinal&&canIn==1){
                        break;
                    }
                    //如果是尖时放电，则记录放电数据
                    if(stateJ== TouState.CRITICAL_PEAK){
                        canIn=2;
                        currentEnergy=0;
                        LocalTime startJ = touPeriodDTOJ.startTime();
                        LocalTime endJ = touPeriodDTOJ.endTime();
                        commands.add(new DispatchCommand(startJ, endJ, -capacity));
                        i=j;
                        break;
                    }
                }
                //有放电机会则记录此次充电数据
                if (canIn>0) {
                    if(canIn==1){
                        //不是尖时放电机会，则记录当前充电值
                        currentEnergy=standardPower;
                    }
                    commands.add(new DispatchCommand(start, end, capacity));
                }
            }else{
                //如果后续的枚举值大于之前记录的枚举排序值，则此次有放电机会，记录放电数据
                if (ordinal > ordinalPre) {
                    currentEnergy=0.0;
                    commands.add(new DispatchCommand(start, end, -capacity));
                }
            }
        }
        commands.sort(Comparator.comparing(DispatchCommand::getStartTime));
        return commands;

    }
    public static void main(String[] args) {
        Set<TouPeriodDTO> periods = Set.of(
                new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(0, 0), LocalTime.of(6, 0)),
                new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(6, 0), LocalTime.of(8, 0)),
                new TouPeriodDTO(TouState.PEAK, LocalTime.of(8, 0), LocalTime.of(12, 0)),
                new TouPeriodDTO(TouState.CRITICAL_PEAK, LocalTime.of(12, 0), LocalTime.of(14, 0)),
                new TouPeriodDTO(TouState.PEAK, LocalTime.of(14, 0), LocalTime.of(15, 0)),
                new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(15, 0), LocalTime.of(18, 0)),
                new TouPeriodDTO(TouState.PEAK, LocalTime.of(18, 0), LocalTime.of(21, 0)),
                new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(21, 0), LocalTime.of(22, 0)),
                new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(22, 0), LocalTime.of(0, 0))
        );

        Set<TouPriceDTO> prices = Set.of(
                new TouPriceDTO(TouState.OFF_PEAK, 0.31),
                new TouPriceDTO(TouState.SHOULDER, 0.76),
                new TouPriceDTO(TouState.PEAK, 0.92),
                new TouPriceDTO(TouState.CRITICAL_PEAK, 1.20)
        );

        TouDTO touDTO = new TouDTO(LocalDate.now(), periods, prices);
        DeviceSpecDTO deviceSpecDTO = new DeviceSpecDTO(50.0, 50.0);
        DispatchContext context = new DispatchContext(touDTO, deviceSpecDTO);

        List<DispatchCommand> commands = dispatch(context);
        for (DispatchCommand command : commands) {
            System.out.println(command);
        }
    }
}
