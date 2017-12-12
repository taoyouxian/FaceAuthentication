package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: MusicMessage
 * @Description: 音乐消息
 * @author Tao
 * @date 2016年3月13日 上午11:48:45
 */
public class MusicMessage extends BaseMessage {

	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
