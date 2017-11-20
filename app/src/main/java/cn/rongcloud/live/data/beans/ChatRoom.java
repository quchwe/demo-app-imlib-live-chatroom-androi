package cn.rongcloud.live.data.beans;




public class ChatRoom {

    private String id;
    private String createTime;
    private String chatLog;
    private String rongyunId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChatLog() {
        return chatLog;
    }

    public void setChatLog(String chatLog) {
        this.chatLog = chatLog;
    }

    public String getRongyunId() {
        return rongyunId;
    }

    public void setRongyunId(String rongyunId) {
        this.rongyunId = rongyunId;
    }
}
