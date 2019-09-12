
package frame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

public final class GetAvatar {

	private static void download(String urlString, String filename, String savePath) throws Exception {

	
		URL url = new URL(urlString);


		URLConnection con = url.openConnection();


		con.setConnectTimeout(5 * 1000);


		InputStream in = con.getInputStream();

		
		byte[] bs = new byte[1024];

		
		int len;


		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		OutputStream out = new FileOutputStream(file.getPath() + "\\" + filename);

	
		while ((len = in.read(bs)) != -1) {
			out.write(bs, 0, len);
		}


		out.close();
		in.close();
	}

	public static ImageIcon getAvatarImage(String id, String relativePath, String avatarUrl) {
		ImageIcon avatar = null;
		try {
			String path = relativePath + id + ".jpg";
			if (!new File(path).exists()) {
				download(avatarUrl, id + ".jpg", relativePath);
			}
			avatar = new ImageIcon(path);
		} catch (Exception e) {
			avatar = new ImageIcon("./res/DefaultHead.jpg");
			System.out.println("获取头像失败，改为默认头像：" + avatarUrl);
		}
		return avatar;
	}
}
