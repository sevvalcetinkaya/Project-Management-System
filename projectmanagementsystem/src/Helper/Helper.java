package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");

	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "empty":
			msg = "Lütfen tüm alanları doldurunuz!";
			break;
		case "success":
			msg = "İşlem başarılı";
			break;
		case "error":
			msg = "Bir hata ile karşılaşıldı!";
			break;
		case "bostablo":
			msg = "Tabloda eleman kalmadı!";
			break;
		default:
			msg = str;

		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		optionPaneChangeButtonText();

		String msg;
		switch (str) {
		case "sure":
			msg = "Bu işlemi gerçekleştirmek istiyor musunuz?";
			break;
		default:
			msg = str;
			break;

		}
		int rs = JOptionPane.showConfirmDialog(null, msg, "Dikkat", JOptionPane.YES_NO_OPTION);
		if (rs == 0) {
			return true;
		} else
			return false;
	}
}