package po;

import java.util.Map;

/**
 * @AUTHOR:0416
 * @DESCRIPTION:返回信息
 * @DATE:2019/10/5
 **/
public class Message {
    private String stateCode;
    private String prompt;
    private Map returnData;

    public Message success(){
        this.setStateCode("200");
        this.setPrompt("执行成功");
        return this;
    }

    public Message fail(){
        this.setStateCode("404");
        this.setPrompt("执行失败");
        return this;
    }

    public Message success(String msg){
        this.setStateCode("200");
        this.setPrompt(msg);
        return this;
    }

    public Message fail(String msg){
        this.setStateCode("404");
        this.setPrompt(msg);
        return this;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Map getReturnData() {
        return returnData;
    }

    public void setReturnData(Map returnData) {
        this.returnData = returnData;
    }
}
