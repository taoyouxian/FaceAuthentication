package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: Article
 * @Description: 响应消息基类, 该包下的消息类型为被动回复消息接口所使用的类型，即为路由表所提供的服务
 * @author Tao
 * @date 2016年3月13日 上午11:46:49
 */
public class BaseMessage {

	// 接收方帐号（收到的OpenID）
	private String ToUserName;

	// 开发者微信号
	private String FromUserName;

	// 消息创建时间 （整型）
	private long CreateTime;

	// 消息类型（text/music/news）
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
