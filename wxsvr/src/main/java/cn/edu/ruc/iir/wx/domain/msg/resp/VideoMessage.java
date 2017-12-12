package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: VideoMessage
 * @Description: 视频消息
 * @author Tao
 * @date 2016年3月13日 上午11:50:01
 */
public class VideoMessage extends BaseMessage {

	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
