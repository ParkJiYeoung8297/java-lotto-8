package lotto.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultDto {
    private Map<String, Integer> winningRecord;
    private String returnRate;

    public ResultDto(Map<String, Integer> winningRecord, String returnRate){
        this.winningRecord = new LinkedHashMap<>();
        this.returnRate  = "";
    }

    public Map<String, Integer> getWinningRecord(){
        return winningRecord;
    }

    public String getReturnRate(){
        return returnRate;
    }
}