package net.skup;

import java.util.List;

import net.skup.model.Pun;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class SwiftyAdapter extends ArrayAdapter<Pun> {


	private final Activity context;
	private List<Pun> list = null;
	
	public SwiftyAdapter(Activity context, List<Pun> objects) {
		super(context, R.layout.row_view, objects);
		this.context = context;
	    this.list = objects;
	}

	/** View Holder class contains all my updatable views.*/
	static class ViewHolder {
		protected EditText statement;
		protected EditText adverb;
	}

	/**
	 * Uses View Holder pattern for smooth scrolling.
	 * http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.row_view, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.adverb = (EditText) view.findViewById(R.id.adverb);
			viewHolder.statement = (EditText) view.findViewById(R.id.statement);
			view.setTag(viewHolder);
			
		} else {
			view = convertView;
		}
	
		
		
		ViewHolder holder = (ViewHolder) view.getTag(); // get all subviews
		holder.adverb.setText(list.get(position).getAdverb());
		holder.statement.setText(list.get(position).getStmt());
		holder.statement.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				final int position = v.getId();
				final EditText stmt = (EditText) v;

				String strValue = stmt.getText().toString();
				Log.i(this.getClass().getName(), "User set EditText value to " + strValue +" pos: "+position);
			}
		});
		return view;
	}
}