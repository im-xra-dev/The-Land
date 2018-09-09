package com.weebly.kyslol.Game.update;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.weebly.kyslol.Game.entities.Inputs;

public class Update {
	private final static String versionURL = "http://kyslol.weebly.com/the-land-version";

	public static String getLatestVersion() throws Exception {
		String data = getData(versionURL);
		return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));
	}

	public static String getDownloadLink() throws Exception {
		String data = getData(versionURL);
		return data.substring(data.indexOf("[downloadLink]") + 14, data.indexOf("[/downloadLink]"));
	}

	public static String getData(String add) throws Exception {
		URL url = new URL(add);
		InputStream html;
		html = url.openStream();

		int c = 0;
		StringBuffer buffer = new StringBuffer("");

		while (c != -1) {
			c = html.read();

			buffer.append((char) c);

		}
		html.close();
		return buffer.toString();

	}

	public static String getDetails() throws Exception {
		String data = getData(versionURL);
		return data.substring(data.indexOf("[Details]") + 9, data.indexOf("[/Details]"));
	}

	public static void download(String folder) {
		String downloadlink;

		try {
			downloadlink = getDownloadLink();

			URL website = new URL(downloadlink);
			File file = new File(folder + "The Land Latest.jar");
					//"C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\The Land\\The Land Latest.jar");
			FileUtils.copyURLToFile(website, file);
		} catch (Exception e) {
			System.out.println("ERROR DOWNLOADING");
			e.printStackTrace();
		}
	}
}

//TODO UPDATE IN OWN SECTION LIKE SAVE also on smts
/*			if (update) {
				g.setColor(new Color(0x000000));
				g.fillRect(235, 0, 600, 20);
				g.setColor(new Color(0xffffff));
				g.setFont(new Font("cambri", 0, 10));
				g.drawString("You are currently using " + version + ".  Version " + latest
						+ " is now available for download at kyslol.weebly.com/the-land!", 240, 10);

				g.setColor(new Color(0x000000));
				g.fillRect(700, 20, 100, 40);
				g.setColor(new Color(0xffffff));
				g.drawString("Details", 730, 40);
				if (Inputs.mb != -1) {
					if (region(700, 100, 20, 40)) {
						if (det) {
							det = false;
							// pause = 0;
						} else {
							det = true;
							// pause = 1;
						}
					}
				}

				if (det) {
					g.setColor(new Color(0x000000));
					g.fillRect(0, 60, 800, 600);

					g.setColor(new Color(0xffffff));
					String d = "";
					int row = 70;
					for (int i = 0; i < Details.length(); i++) {
						char c = Details.charAt(i);
						if (c == '{') {
							g.drawString(d, 10, row);
							d = "";
							// row += 15;
							// i++;
							while (c == '{') {
								i++;
								row += 15;
								c = Details.charAt(i);
							}
						}
						if (c == '}') {
							// System.out.println(c);
							continue;
						}
						d = d + Details.charAt(i);

					}

					// System.out.println(Details);

				}
			}
*/
