package com.holub.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * An interface for Date selection.
 * 
 * See {@link DateSelectorPanel} for a discussion of how date selectors look
 * and work. This interface defines the public methods of that class to allow
 * for the use of a Gang-of-Four Decorator to add optional labels.
 * <p>
 * Most of the interface methods have to do with action-listeners support. The
 * listeners are notified in two situations, which can be distinguished from one
 * another by calling {@link ActionEvent#getID}: <table border=1 cellspacing=0
 * cellpadding=2>
 * <tr>
 * <td><b><code>getID()</code> Returns:</b></td>
 * <td><b>Description:</b></td>
 * </tr>
 * <tr>
 * <td>{@link #CHANGE_ACTION}</td>
 * <td> This event is sent when the calendar panel changes the displayed month
 * or year (tyically because some sort of navigator bar asked it to). Call
 * <code>event</code>.{@link ActionEvent#getActionCommand getActionCommand()}
 * to get a string holding the current (after the scroll) month and year. You
 * can also call {@link #getCurrentDate()} to get get the date the user
 * selected. </td>
 * </tr>
 * <tr>
 * <td>{@link #SELECT_ACTION}</td>
 * <td> Sent every time the user clicks on a date. Call <code>event</code>.{@link ActionEvent#getActionCommand getActionCommand()}
 * to get a string representing the selected date. (This string takes the same
 * form as the one returned by {@link Date#toString}.) You can also call
 * {@link #getSelectedDate()} to get get the date the user selected. </td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * </tr>
 * </table> The following moduleple demonstrates how to create a single JPanel
 * that contains a title displaying the name of the current month and year as
 * well as a calendar for that date. The ActionListener automatically updates
 * the label every time the user navigates to another month. (You will rarely
 * have to do this, since the {@link TitledDateSelector} class will handle
 * exactly that problem for you, but the moduleple demonstrates the technique.)
 * 
 * <pre>
 * private static JPanel createCalendarPane(DateSelector s) {
 * 	JPanel panel = new JPanel();
 * 	panel.setLayout(new BorderLayout());
 * 
 * 	final JLabel month = new JLabel(&quot;MMM YYYY&quot;);
 * 	s.addActionListener(new ActionListener() {
 * 		public void actionPerformed(ActionEvent e) {
 * 			if (e.getID() == DateSelector.CHANGE_ACTION)
 * 				month.setText(e.getActionCommand());
 * 			else
 * 				System.out.println(e.getActionCommand());
 * 		}
 * 	});
 * 	panel.add(month, BorderLayout.NORTH);
 * 	panel.add(s, BorderLayout.CENTER);
 * 	return panel;
 * }
 * </pre>
 * 
 * <p>
 * Classes that implement this interface must also <code>extend Container</code>
 * or some <code>Container</code> derivative. (You can't mandate this in the
 * compiler because Container is not an interface, so can't be a base class of
 * DateSelector.)
 * 
 * @see DateSelectorPanel
 * @see DateSelectorDialog
 */

public interface DateSelector {
	public static final int CHANGE_ACTION = 0;

	public static final int SELECT_ACTION = 1;

	public void addActionListener(ActionListener l);

	public void removeActionListener(ActionListener l);

	public Date getSelectedDate();

	public Date getCurrentDate();

	/** Must work just like {@link Calendar#roll(int,boolean)} */
	public void roll(int flag, boolean up);

	/** Must work just like {@link Calendar#get(int)} */
	public int get(int flag);
}
