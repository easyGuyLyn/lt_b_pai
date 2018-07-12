package com.dawoo.lotterybox.bean.record;

import android.os.Parcel;
import android.os.Parcelable;

import com.dawoo.lotterybox.net.BaseHttpResult;

import java.util.List;

/**
 * Created by benson on 18-2-18.
 */

public class NoteRecordHisData extends BaseHttpResult implements Parcelable {

    private List<NoteRecordHis> data;
    private Count extend;

    public Count getExtend() {
        return extend;
    }

    public void setExtend(Count extend) {
        this.extend = extend;
    }

    public List<NoteRecordHis> getData() {
        return data;
    }

    public void setData(List<NoteRecordHis> data) {
        this.data = data;
    }


    public static class Count implements Parcelable {
        private int totalCount;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.totalCount);
        }

        public Count() {
        }

        protected Count(Parcel in) {
            this.totalCount = in.readInt();
        }

        public static final Creator<Count> CREATOR = new Creator<Count>() {
            @Override
            public Count createFromParcel(Parcel source) {
                return new Count(source);
            }

            @Override
            public Count[] newArray(int size) {
                return new Count[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeParcelable(this.extend, flags);
    }

    public NoteRecordHisData() {
    }

    protected NoteRecordHisData(Parcel in) {
        this.data = in.createTypedArrayList(NoteRecordHis.CREATOR);
        this.extend = in.readParcelable(Count.class.getClassLoader());
    }

    public static final Parcelable.Creator<NoteRecordHisData> CREATOR = new Parcelable.Creator<NoteRecordHisData>() {
        @Override
        public NoteRecordHisData createFromParcel(Parcel source) {
            return new NoteRecordHisData(source);
        }

        @Override
        public NoteRecordHisData[] newArray(int size) {
            return new NoteRecordHisData[size];
        }
    };
}
