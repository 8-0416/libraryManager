package po;

import java.util.Map;

/**
 * @author 0416
 * 返回信息
 * @date 2019/10/5
 **/
public class Message {
    // 状态码
    private String stateCode;
    // 提示语
    private String prompt;
    // 返回数据
    private Map returnData;

    public Message setCodeAndPrompt(String stateCode, String prompt){
        this.setStateCode(stateCode);
        this.setPrompt(prompt);
        return this;
    }

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
