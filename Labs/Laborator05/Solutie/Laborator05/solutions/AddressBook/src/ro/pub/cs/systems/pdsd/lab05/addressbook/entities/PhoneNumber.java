package ro.pub.cs.systems.pdsd.lab05.addressbook.entities;

import android.os.Parcel;
import android.os.Parcelable;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;

public class PhoneNumber extends Entity {
	
	public PhoneNumber(String type, int value) {
		super(type, value);
	}
	
	public PhoneNumber(String type, String value) {
		super(type, value);
	}
	
	public PhoneNumber(Parcel parcel) {
		String[] data = new String[2];

		parcel.readStringArray(data);
        this.setValue(data[0]);
        this.setType(Integer.parseInt(data[1]));
	}	

	@Override
	public int convertTypeToIndex(String type) {
		if (type.equals(Constants.TYPE_HOME)) {
			return Constants.PHONE_TYPE_HOME;
		}
		if (type.equals(Constants.TYPE_MOBILE)) {
			return Constants.PHONE_TYPE_MOBILE;
		}		
		if (type.equals(Constants.TYPE_WORK)) {
			return Constants.PHONE_TYPE_WORK;
		}
		return Constants.PHONE_TYPE_OTHER;		
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
    
    public static final Parcelable.Creator<PhoneNumber> CREATOR = new Parcelable.Creator<PhoneNumber>() {
        public PhoneNumber createFromParcel(Parcel parcel) {
            return new PhoneNumber(parcel); 
        }

        public PhoneNumber[] newArray(int size) {
            return new PhoneNumber[size];
        }
    };	
}
