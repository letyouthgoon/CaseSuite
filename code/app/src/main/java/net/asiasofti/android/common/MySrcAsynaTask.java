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
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import net.asiasofti.android.handler.ImageLoader;
import net.asiasofti.android.handler.ImageLoader.ImageCallback;

public class MySrcAsynaTask extends AsyncTask<String, Void, String>
{
  private ImageView iv;
  private String themb;

  public MySrcAsynaTask(String paramString, ImageView paramImageView)
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
            MySrcAsynaTask.this.iv.setImageDrawable(new BitmapDrawable(paramAnonymousBitmap));
            return;
          }
          MySrcAsynaTask.this.iv.setImageResource(2130837525);
        }
      });
      return;
    }
    this.iv.setImageResource(2130837525);
  }
}
