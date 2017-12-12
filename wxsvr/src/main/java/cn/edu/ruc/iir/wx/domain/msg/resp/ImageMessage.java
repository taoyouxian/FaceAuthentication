package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: ImageMessage
 * @Description: 图片消息
 * @author Tao
 * @date 2016年3月13日 上午11:48:00
 */
public class ImageMessage extends BaseMessage {

	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
