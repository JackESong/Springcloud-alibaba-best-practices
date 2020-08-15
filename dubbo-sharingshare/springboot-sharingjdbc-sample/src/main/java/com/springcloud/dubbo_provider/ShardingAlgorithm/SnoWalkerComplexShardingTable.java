package com.springcloud.dubbo_provider.ShardingAlgorithm;

import com.springcloud.dubbo_provider.Enum.DbAndTableEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

@Slf4j
public class SnoWalkerComplexShardingTable implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
//        log.info("collection----=====-------->>>" + JSON.toJSONString(collection));
//        log.info("collection----=====-------->>>" + JSON.toJSONString(complexKeysShardingValue));
        Collection<String> shardingResults = new ArrayList<>();
        // 表名前缀
        String logicTableName = complexKeysShardingValue.getLogicTableName();
        // 分片规则所需字段
        Map<String, Collection<Long>> columnsMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        List orderIds = Arrays.asList(columnsMap.get("order_id").toArray());
        for (Object orderId:orderIds) {
            Long index = getIndex((Long) orderId);
            //循环匹配数据表源
            for (String availableTargetName : collection){
                if (availableTargetName.endsWith("_"+index)) {
                    shardingResults.add(availableTargetName);
                    break;
                }
            }
        }
        return shardingResults;
    }

    public Long getIndex (Long orderId) {
        Integer tableSize = DbAndTableEnum.T_ORDER.getTableSize();
        Integer dbSize = DbAndTableEnum.T_ORDER.getDbSize();
        return orderId % (dbSize* tableSize) % tableSize + 1;
    }

}
