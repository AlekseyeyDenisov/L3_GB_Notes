package ru.dw.gbnotes.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class NotesEntity implements Parcelable {

    @PrimaryKey
    private final Long id;
    private String heading;
    private String description;
    private String date;

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

    public Long getId() {
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

    public NotesEntity(Long id, String heading, String description, String date) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.date = date;
    }

    protected NotesEntity(Parcel in) {
        id = readOptLong(in);
        heading = in.readString();
        description = in.readString();
        date = in.readString();
    }

    private Long readOptLong(Parcel in) {
        if (in.readByte() == 0) {
            return null;
        } else {
            return in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(heading);
        dest.writeString(description);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
