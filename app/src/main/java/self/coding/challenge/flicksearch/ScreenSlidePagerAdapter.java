package self.coding.challenge.flicksearch;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the viewpager on the screen
 */
class ScreenSlidePagerAdapter extends PagerAdapter {

    private List<IndivPhoto> photos = new ArrayList<>();
    private Context mContext;

    /**
     * Constructor for the adapter
     *
     * @param context calling context
     * @param photos  list of images to be displayed
     */
    ScreenSlidePagerAdapter(Context context, List<IndivPhoto> photos) {
        this.photos = photos;
        this.mContext = context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.slide_layout, container, false);
        ViewHolder viewHolder;
        if (inflate.getTag() == null) {
            AppCompatImageView imgv_image = (AppCompatImageView) inflate.findViewById(R.id.imgv_image);
            TextView txtv_image_title = (TextView) inflate.findViewById(R.id.txtv_image_title);
            viewHolder = new ViewHolder(imgv_image, txtv_image_title);
            inflate.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) inflate.getTag();
        }

        Picasso.with(mContext)
                .load(photos.get(position).getUrl_z())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error) //
                .fit()
                .tag(mContext)
                .into(viewHolder.imgv_image);

        viewHolder.txtv_image_title.setText(photos.get(position).getTitle());
        container.addView(inflate);
        return inflate;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }

    /**
     * ViewHolder pattern class
     */
    private class ViewHolder {
        AppCompatImageView imgv_image;
        TextView txtv_image_title;

        ViewHolder(AppCompatImageView imgv_image, TextView txtv_image_title) {
            this.imgv_image = imgv_image;
            this.txtv_image_title = txtv_image_title;
        }
    }
}
