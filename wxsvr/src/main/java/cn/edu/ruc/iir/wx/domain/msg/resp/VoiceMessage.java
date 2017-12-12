package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: VoiceMessage
 * @Description: 语音消息
 * @author Tao
 * @date 2016年3月13日 上午11:49:35
 */
public class VoiceMessage extends BaseMessage {

	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
