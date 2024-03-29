/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package uk.ac.sheffield.dcs.smdStudio.framework.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import uk.ac.sheffield.dcs.smdStudio.framework.resources.ResourceBundleConstant;


/**
 * Violet's splash screen
 * 
 * @author Alexandre de Pellegrin
 * 
 */
@SuppressWarnings("serial")
public class SplashScreen extends JWindow {

	public SplashScreen() {
		super();
		prepare();
	}

	public SplashScreen(EditorFrame editorFrame) {
		super(editorFrame);
		prepare();
	}

	private void prepare() {
		ResourceBundle bundle = ResourceBundle.getBundle(
				ResourceBundleConstant.ABOUT_STRINGS, Locale.getDefault());
		String imagePath = bundle.getString("dialog.about.image");
		JLabel l = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
		getContentPane().add(l, BorderLayout.CENTER);
		pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = l.getPreferredSize();

		// Put image at the middle of the screen
		setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));
	}

	public static void displayOverEditor(EditorFrame editorFrame, int waitTime) {
		final SplashScreen splashScreen = new SplashScreen(editorFrame);

		splashScreen.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				splashScreen.setVisible(false);
				splashScreen.dispose();
			}
		});
		final int pause = waitTime;
		final Runnable closerRunner = new Runnable() {
			public void run() {
				splashScreen.setVisible(false);
				splashScreen.dispose();
			}
		};
		Runnable waitRunner = new Runnable() {
			public void run() {
				try {
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				} catch (Exception e) {
					e.printStackTrace();
					// can catch InvocationTargetException
					// can catch InterruptedException
				}
			}
		};

		splashScreen.setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}

}
