package com.example.androidcodes.photoeditingapp.Frames.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.androidcodes.photoeditingapp.Frames.CustomGallery.Temp.CustomGallery;
import com.example.androidcodes.photoeditingapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {

    ImageLoader imageLoader;

    //private Context mContext;

    private LayoutInflater infalter;

    private ArrayList<CustomGallery> data = new ArrayList<>();

    public GalleryAdapter(Context c, ImageLoader imageLoader) {

        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //mContext = c;

        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CustomGallery getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*public void setMultiplePick(boolean isMultiplePick) {

        this.isActionMultiplePick = isMultiplePick;

    }*/

    public void selectAll(boolean selection) {

        for (int i = 0; i < data.size(); i++) {

            data.get(i).isSeleted = selection;

        }

        notifyDataSetChanged();
    }

    public boolean isAllSelected() {

        boolean isAllSelected = true;

        for (int i = 0; i < data.size(); i++) {

            if (!data.get(i).isSeleted) {

                isAllSelected = false;

                break;

            }
        }

        return isAllSelected;
    }

    public boolean isAnySelected() {

        boolean isAnySelected = false;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).isSeleted) {

                isAnySelected = true;

                break;

            }
        }

        return isAnySelected;
    }

    public ArrayList<CustomGallery> getSelected() {

        ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).isSeleted) {

                dataT.add(data.get(i));

            }
        }

        return dataT;

    }

    public void addAll(ArrayList<CustomGallery> files) {

        try {

            this.data.clear();

            this.data.addAll(files);

        } catch (Exception e) {

            e.printStackTrace();

        }

        notifyDataSetChanged();
    }

    public String changeSelection(View v, int position) {

        if (data.get(position).isSeleted) {

            //data.get(position).isSeleted = false;

            return "";

        } else {

            data.get(position).isSeleted = true;

            return data.get(position).sdcardPath.toString();

        }

        /*((ViewHolder) v.getTag()).imgQueueMultiSelected.setSelected(data
                .get(position).isSeleted);*/
    }

    public void ChangeSelection(String sdcardPath) {

        int position = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).sdcardPath.equals(sdcardPath))
                position = i;
        }

        if (data.get(position).isSeleted) {

            data.get(position).isSeleted = false;

        } else {

            data.get(position).isSeleted = true;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {

            convertView = infalter.inflate(R.layout.gallery_item, null);

            holder = new ViewHolder();

            holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);

            /*holder.imgQueueMultiSelected = (ImageView) convertView.
                    findViewById(R.id.imgQueueMultiSelected);*/

            /*if (isActionMultiplePick) {

                holder.imgQueueMultiSelected.setVisibility(View.VISIBLE);

            } else {

                holder.imgQueueMultiSelected.setVisibility(View.GONE);

            }*/

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        holder.imgQueue.setTag(position);

        try {

           /* Bitmap b = BitmapFactory.decodeFile(data.get(position).sdcardPath.toString());

            holder.imgQueue.
                    setImageBitmap(ScalingUtilities.
                            createScaledBitmap(b, 720, 720, ScalingUtilities.ScalingLogic.CROP));*/

            imageLoader.displayImage("file://" + data.get(position).sdcardPath, holder.imgQueue,
                    new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                            holder.imgQueue
                                    .setImageResource(R.drawable.noimagefound);

                            super.onLoadingStarted(imageUri, view);
                        }
                    });

            /*if (isActionMultiplePick) {

                holder.imgQueueMultiSelected.setSelected(data.get(position).isSeleted);

            }*/
        } catch (Exception e) {

            e.printStackTrace();

        }

        return convertView;
    }

    public void clear() {

        data.clear();

        notifyDataSetChanged();

    }

    public class ViewHolder {

        ImageView imgQueue;

        //ImageView imgQueueMultiSelected;

    }
}
