package task.ab.com.task;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang.ding on 2016/4/7.
 */
public class GetContactsInfo {
    List<ContactsInfo> localList;
    List<ContactsInfo> SIMList;
    Context context;
    ContactsInfo contactsInfo;
    ContactsInfo SIMContactsInfo;

    public GetContactsInfo(Context context) {
        localList = new ArrayList<ContactsInfo>();
        SIMList = new ArrayList<ContactsInfo>();
        this.context = context;

    }

    // ----------------得到本地联系人信息-------------------------------------
    public List<ContactsInfo> getLocalContactsInfos() {

        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        //向下移动光标
        if(cursor != null){
            try{
                while(cursor.moveToNext())
                {
                    //取得联系人名字
                    int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                    String contact = cursor.getString(nameFieldColumnIndex);
                    //取得电话号码
                    String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
                    int i = 0;
                    while(phone.moveToNext())
                    {
                        if(i > 4)
                            break;
                        String PhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        System.out.println(contact + ":" + PhoneNumber);
                        i++;
                    }
                }
            }catch (Exception e){}
            finally {
                cursor.close();
            }

        }

//        String str[] = { ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
//                ContactsContract.CommonDataKinds.Phone.PHOTO_ID };
//        Cursor cur = cr.query(
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null,
//                null, null);
//
//        if (cur != null) {
//            try{
//                while (cur.moveToNext()) {
//                    contactsInfo = new ContactsInfo();
//                    contactsInfo.setContactsPhone(cur.getString(cur
//                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));// 得到手机号码
//                    contactsInfo.setContactsName(cur.getString(cur
//                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
//                    // contactsInfo.setContactsPhotoId(cur.getLong(cur.getColumnIndex(Phone.PHOTO_ID)));
//                    long contactid = cur.getLong(cur
//                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
//                    long photoid = cur.getLong(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
//                    // 如果photoid 大于0 表示联系人有头像 ，如果没有给此人设置头像则给他一个默认的
//                    if (photoid > 0) {
//                        Uri uri = ContentUris.withAppendedId(
//                                ContactsContract.Contacts.CONTENT_URI, contactid);
//                        InputStream input = ContactsContract.Contacts
//                                .openContactPhotoInputStream(cr, uri);
//                        contactsInfo.setBitmap(BitmapFactory.decodeStream(input));
//                    } else {
////                    contactsInfo.setBitmap(BitmapFactory.decodeResource(
////                            context.getResources(), R.drawable.));
//                    }
//                    System.out.println(contactsInfo.getContactsName() + " ----->>>>>>" + contactsInfo.getContactsPhone());
//                    localList.add(contactsInfo);
//
//                }
//            }catch (Exception e){
//
//            }finally {
//                cur.close();
//            }
//
//
//        }

        return localList;

    }

    public List<ContactsInfo> getSIMContactsInfos() {
        TelephonyManager mTelephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        System.out.println("---------SIM--------");
        ContentResolver cr = context.getContentResolver();
        final String SIM_URI_ADN = "content://icc/adn";// SIM卡


        Uri uri = Uri.parse(SIM_URI_ADN);
        Cursor cursor = cr.query(uri, null, null, null, null);
        if(cursor != null){
            while (cursor.moveToFirst()) {
                SIMContactsInfo = new ContactsInfo();
                SIMContactsInfo.setContactsName(cursor.getString(cursor
                        .getColumnIndex("name")));
                SIMContactsInfo
                        .setContactsPhone(cursor.getString(cursor
                                .getColumnIndex("number")));
//            SIMContactsInfo
//                    .setBitmap(BitmapFactory.decodeResource(
//                            context.getResources(),
//                            R.drawable.ic_launcher));
                SIMList.add(SIMContactsInfo);
            }

            cursor.close();
        }


        return SIMList;
    }
}
