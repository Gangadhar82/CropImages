package com.mmadapps.cropimages;

/**
 * Created by gangadhar.g on 8/19/2015.
 */

import android.content.res.Resources;
import android.widget.ImageView;

import com.cesards.cropimageview.CropImageView;


public class CustomCropActivity extends CropActivity {

    private CropImageView.CropType[] imageCrops = {
            // Zombie sample
            CropImageView.CropType.CENTER_TOP,
            CropImageView.CropType.LEFT_CENTER,
            CropImageView.CropType.CENTER_BOTTOM,
            // Ball sample
            CropImageView.CropType.LEFT_CENTER,
            CropImageView.CropType.CENTER_TOP,
            CropImageView.CropType.RIGHT_CENTER,
    };

    private int[] images = {
            R.drawable.zombie,
            R.drawable.zombie,
            R.drawable.zombie,
            R.drawable.images,
            R.drawable.images,
            R.drawable.images,
            -1,
    };

    @Override
    protected int getImagesCount() {
        return images.length;
    }

    @Override
    protected ImageView instantiatePagerItem(int position) {
        TestForegroundCropImageView testCropImageView = new TestForegroundCropImageView(CustomCropActivity.this);

        int image = images[position];
        if (image != -1) {
            final Resources res = getResources();
            testCropImageView.setImageDrawable(res.getDrawable(image));
            testCropImageView.setForeground(res.getDrawable(R.drawable.shape_grad_black_transp_70));
            final CropImageView.CropType cropType = imageCrops[position];
            testCropImageView.setCropType(cropType);
            testCropImageView.setId(cropType.getCrop());
        } else {
            testCropImageView.setImageDrawable(null);
        }

        return testCropImageView;
    }
}

