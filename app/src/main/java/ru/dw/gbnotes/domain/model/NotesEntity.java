package ru.dw.gbnotes.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesEntity implements Parcelable {

    private String id;
    private String heading;
    private String description;
    private String date;

    public NotesEntity(String id, String heading, String description, String date) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.date = date;
    }

    protected NotesEntity(Parcel in) {
        id = in.readString();
        heading = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<NotesEntity> CREATOR = new Creator<NotesEntity>() {
        @Override
        public NotesEntity createFromParcel(Parcel in) {
            return new NotesEntity(in);
        }

        @Override
        public NotesEntity[] newArray(int size) {
            return new NotesEntity[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(heading);
        parcel.writeString(description);
        parcel.writeString(date);

    }
}
