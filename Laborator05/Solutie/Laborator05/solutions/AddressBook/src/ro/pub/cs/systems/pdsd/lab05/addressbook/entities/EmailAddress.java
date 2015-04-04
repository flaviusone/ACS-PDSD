package ro.pub.cs.systems.pdsd.lab05.addressbook.entities;

import android.os.Parcel;
import android.os.Parcelable;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;

public class EmailAddress extends Entity {
	
	public EmailAddress(String type, int value) {
		super(type, value);
	}
	
	public EmailAddress(String type, String value) {
		super(type, value);
	}	
	
	public EmailAddress(Parcel parcel) {
		String[] data = new String[2];

		parcel.readStringArray(data);
        this.setValue(data[0]);
        this.setType(Integer.parseInt(data[1]));
	}	
	
	@Override
	public int convertTypeToIndex(String type) {
		if (type.equals(Constants.TYPE_HOME)) {
			return Constants.EMAIL_TYPE_HOME;
		}
		if (type.equals(Constants.TYPE_WORK)) {
			return Constants.EMAIL_TYPE_WORK;
		}
		return Constants.EMAIL_TYPE_OTHER;		
	}
	
	
	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
    	destination.writeStringArray(new String[] {
    			this.getValue(),
                String.valueOf(this.getType())
        });
    }
    
    public static final Parcelable.Creator<EmailAddress> CREATOR = new Parcelable.Creator<EmailAddress>() {
        public EmailAddress createFromParcel(Parcel parcel) {
            return new EmailAddress(parcel); 
        }

        public EmailAddress[] newArray(int size) {
            return new EmailAddress[size];
        }
    };	

}
