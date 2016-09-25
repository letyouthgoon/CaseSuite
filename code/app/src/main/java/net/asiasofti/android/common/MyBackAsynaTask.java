 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.common;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import net.asiasofti.android.handler.ImageLoader;
import net.asiasofti.android.handler.ImageLoader.ImageCallback;

public class MyBackAsynaTask extends AsyncTask<String, Void, String>
{
  private ImageView iv;
  private String themb;

  public MyBackAsynaTask(String paramString, ImageView paramImageView)
  {
    this.themb = paramString;
    this.iv = paramImageView;
  }

  protected String doInBackground(String[] paramArrayOfString)
  {
    if (this.themb != null)
      return this.themb;
    return null;
  }

  public Bitmap getBitmap(Bitmap paramBitmap, int paramInt)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    float f = paramInt / i;
    localMatrix.postScale(f, f);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }

  protected void onCancelled()
  {
    super.onCancelled();
  }

  protected void onPostExecute(String paramString)
  {
    super.onPostExecute(paramString);
    if ((paramString != null) && (!"".equals(paramString)) && (!"null".equals(paramString)))
    {
      ImageLoader.getInstance().asyncLoadBitmap(paramString, new ImageLoader.ImageCallback()
      {
        public void imageLoaded(Bitmap paramAnonymousBitmap, String paramAnonymousString)
        {
          if (paramAnonymousBitmap != null)
          {
            Bitmap localBitmap = MyBackAsynaTask.this.getBitmap(paramAnonymousBitmap, paramAnonymousBitmap.getWidth());
            MyBackAsynaTask.this.iv.setBackgroundDrawable(new BitmapDrawable(localBitmap));
            return;
          }
          MyBackAsynaTask.this.iv.setBackgroundResource(2130837525);
        }
      });
      return;
    }
    this.iv.setBackgroundResource(2130837525);
  }
}
