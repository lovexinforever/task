package task.ab.com.task;

import android.graphics.Bitmap;

/**
 * Created by yang.ding on 2016/4/7.
 */
public class ContactsInfo {
    private String contactsPhone;
    private String contactsName;
    private Bitmap bitmap;


    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
