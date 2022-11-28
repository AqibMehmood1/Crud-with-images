package aqib1.example.image_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Image> ImageList;

    public ImageListAdapter(Context context, int layout, ArrayList<Image> List) {
        this.context = context;
        this.layout = layout;
        this.ImageList = List;
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return ImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.img);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Image image = ImageList.get(position);

        holder.txtName.setText(image.getName());
        holder.txtPrice.setText(image.getPrice());

        byte[] ImageIco = image.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(ImageIco, 0, ImageIco.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
