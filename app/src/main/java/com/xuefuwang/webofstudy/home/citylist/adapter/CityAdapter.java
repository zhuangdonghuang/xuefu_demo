package com.xuefuwang.webofstudy.home.citylist.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.citylist.widget.ContactListAdapter;
import com.xuefuwang.webofstudy.home.citylist.widget.ContactItemInterface;

/**
 * 城市列表的Adapter
 */
public class CityAdapter extends ContactListAdapter
{

	public CityAdapter(Context _context, int _resource,
			List<ContactItemInterface> _items)
	{
		super(_context, _resource, _items);
	}

	public void populateDataForRow(View parentView, ContactItemInterface item,
			int position)
	{
		View infoView = parentView.findViewById(R.id.infoRowContainer);
		TextView nicknameView = (TextView) infoView
				.findViewById(R.id.cityName);

		nicknameView.setText(item.getDisplayInfo());
	}

}
