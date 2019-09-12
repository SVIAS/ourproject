
package frame;

import javax.swing.JLabel;

public class FriendModel extends GroupModel {
	private String fOnline;
	private JLabel fOnlineLabel;
	
	private static final long serialVersionUID = 1L;

	public FriendModel(String fAvatar, String fName, String fTrades, String fid, String fOnline) {
		super(fAvatar, fName, fTrades, fid, "./Data/Avatar/User/");
		this.fOnline = fOnline;
		fOnlineLabel = new JLabel(fOnline);
		fOnlineLabel.setBounds(232, 4, 42, 22);
		this.add(fOnlineLabel);
	}

	public String getOnline() {
		return fOnline;
	}

	public String getfOnline() {
		return fOnline;
	}

	public void setfOnline(String fOnline) {
		this.fOnline = fOnline;
		fOnlineLabel.setText(fOnline);
	}
}