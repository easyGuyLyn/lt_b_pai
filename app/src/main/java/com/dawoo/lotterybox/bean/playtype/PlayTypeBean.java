package com.dawoo.lotterybox.bean.playtype;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-2-19.
 * 官方玩法
 */

public class PlayTypeBean implements Parcelable {

    private String totalType; //总类型   如：官方
    private String parentTitle;//大类
    private String type; //小类

    private List<PlayBean> mPlayBeans;

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getTotalType() {
        return totalType;
    }

    public void setTotalType(String totalType) {
        this.totalType = totalType;
    }

    public List<PlayBean> getPlayBeans() {
        return mPlayBeans;
    }

    public void setPlayBeans(List<PlayBean> playBeans) {
        mPlayBeans = playBeans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


//    {
//        "backAccStatus": "0",
//            "backAccount": "0/13%",
//            "playTypeCode":"one_digital";//玩法代号
//            "id": "52",
//            "isSelected": false,
//            "mainType": "二星",
//            "odds": "0.980/0.850",
//            "playTypeAb": "后二直选跨度A面",
//            "playTypeExplain": "从0-9中选择1个号码，所选数值等于开奖号码的后2位最大与最小数字相减之差，即为中奖。",
//            "playTypeName": "跨度A面",
//            "prizeBet": "1",
//            "prizeLevel": "",
//            "sampleBet": "投注方案：跨度9[br]开奖号码：后二位90或09，即中后二直选。",
//            "scheme": "后二直选",
//            "singleBet": "1",
//            "singleExplain": "竞猜后两位开奖号码之差",
//            "totalBet": "100"
//    }


    public static class PlayBean implements Parcelable {
        private String mainType;//主类型
        private String betCode; //具体玩法代号
        private String playTypeName;//玩法名称
        private String playTypeCode;//玩法代号
        private String scheme;//方案
        private String sampleBet;//投注示例
        private String sampleOpen;//开奖示例
        private String playTypeExplain;//玩法说明
        private String singleExplain;//简略说明
        private int maxWinning;  //最大可中奖注数
        private boolean isRelation = true;  //多布局间是否关联
        private List<LayoutBean> mLayoutBeans; //布局

        private boolean isSelected;  //是否选中当前类型

        public String getMainType() {
            return mainType;
        }

        public void setMainType(String mainType) {
            this.mainType = mainType;
        }

        public String getBetCode() {
            return betCode;
        }

        public void setBetCode(String betCode) {
            this.betCode = betCode;
        }

        public String getPlayTypeName() {
            return playTypeName;
        }

        public void setPlayTypeName(String playTypeName) {
            this.playTypeName = playTypeName;
        }

        public String getPlayTypeCode() {
            return playTypeCode;
        }

        public void setPlayTypeCode(String playTypeCode) {
            this.playTypeCode = playTypeCode;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getSampleBet() {
            return sampleBet;
        }

        public void setSampleBet(String sampleBet) {
            this.sampleBet = sampleBet;
        }

        public String getSampleOpen() {
            return sampleOpen;
        }

        public void setSampleOpen(String sampleOpen) {
            this.sampleOpen = sampleOpen;
        }

        public String getPlayTypeExplain() {
            return playTypeExplain;
        }

        public void setPlayTypeExplain(String playTypeExplain) {
            this.playTypeExplain = playTypeExplain;
        }

        public String getSingleExplain() {
            return singleExplain;
        }

        public void setSingleExplain(String singleExplain) {
            this.singleExplain = singleExplain;
        }

        public int getMaxWinning() {
            return maxWinning;
        }

        public void setMaxWinning(int maxWinning) {
            this.maxWinning = maxWinning;
        }

        public boolean isRelation() {
            return isRelation;
        }

        public void setRelation(boolean relation) {
            isRelation = relation;
        }

        public List<LayoutBean> getLayoutBeans() {
            return mLayoutBeans;
        }

        public void setLayoutBeans(List<LayoutBean> layoutBeans) {
            mLayoutBeans = layoutBeans;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean select) {
            isSelected = select;
        }

        public static class LayoutBean implements Parcelable {
            private int layoutType; //布局类型  0-常规   2-输入框布局  3-其他类型待添加
            private String layoutTitle;//布局标题 如：万位
            private int showRightMenuType; //控制布局右侧（全大小双单清）的显示   0-全显    1-全清   2- 全隐藏
            private int startNumber;   //一些布局里号码从1开始（如和值）；
            private int childItemCount;   //控制布局内有recyclerView时的item数量
            private int itemType; // item的类型，  0- 球   1-方框  3-其它
            private int selectMin = 1;  //常规：单个布局最少选择几个球，默认1个   输入框：规定输入字符最小长度
            private int selectMax = 0; //常规：单个布局最多选择几个球，默认0个无限制   输入框：规定输入字符最大长度，0无限制
            private String splitCharacter; //分割符
            private boolean isSelectEqual = true; //多布局间是否可以选相同号码
            private List<ChildLayoutBean> mChildLayoutBeans;

            public int getLayoutType() {
                return layoutType;
            }

            public void setLayoutType(int layoutType) {
                this.layoutType = layoutType;
            }

            public String getLayoutTitle() {
                return layoutTitle;
            }

            public void setLayoutTitle(String layoutTitle) {
                this.layoutTitle = layoutTitle;
            }

            public int getShowRightMenuType() {
                return showRightMenuType;
            }

            public void setShowRightMenuType(int showRightMenuType) {
                this.showRightMenuType = showRightMenuType;
            }

            public int getStartNumber() {
                return startNumber;
            }

            public void setStartNumber(int startNumber) {
                this.startNumber = startNumber;
            }

            public int getChildItemCount() {
                return childItemCount;
            }

            public void setChildItemCount(int childItemCount) {
                this.childItemCount = childItemCount;
            }

            public int getItemType() {
                return itemType;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            public int getSelectMin() {
                return selectMin;
            }

            public void setSelectMin(int selectMin) {
                this.selectMin = selectMin;
            }

            public int getSelectMax() {
                return selectMax;
            }

            public void setSelectMax(int selectMax) {
                this.selectMax = selectMax;
            }

            public String getSplitCharacter() {
                return splitCharacter;
            }

            public void setSplitCharacter(String splitCharacter) {
                this.splitCharacter = splitCharacter;
            }

            public boolean isSelectEqual() {
                return isSelectEqual;
            }

            public void setSelectEqual(boolean selectEqual) {
                isSelectEqual = selectEqual;
            }

            public List<ChildLayoutBean> getChildLayoutBeans() {
                return mChildLayoutBeans;
            }

            public void setChildLayoutBeans(List<ChildLayoutBean> childLayoutBeans) {
                mChildLayoutBeans = childLayoutBeans;
            }

            public static class ChildLayoutBean implements Parcelable {
                private String number; //数字
                private int numberRelevant = -1; //冷热或遗漏

                private boolean isSelected; //是否被选中

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public int getNumberRelevant() {
                    return numberRelevant;
                }

                public void setNumberRelevant(int numberRelevant) {
                    this.numberRelevant = numberRelevant;
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.number);
                    dest.writeInt(this.numberRelevant);
                    dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
                }

                public ChildLayoutBean() {
                }

                protected ChildLayoutBean(Parcel in) {
                    this.number = in.readString();
                    this.numberRelevant = in.readInt();
                    this.isSelected = in.readByte() != 0;
                }

                public static final Creator<ChildLayoutBean> CREATOR = new Creator<ChildLayoutBean>() {
                    @Override
                    public ChildLayoutBean createFromParcel(Parcel source) {
                        return new ChildLayoutBean(source);
                    }

                    @Override
                    public ChildLayoutBean[] newArray(int size) {
                        return new ChildLayoutBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.layoutType);
                dest.writeString(this.layoutTitle);
                dest.writeInt(this.showRightMenuType);
                dest.writeInt(this.startNumber);
                dest.writeInt(this.childItemCount);
                dest.writeInt(this.itemType);
                dest.writeInt(this.selectMin);
                dest.writeInt(this.selectMax);
                dest.writeString(this.splitCharacter);
                dest.writeByte(this.isSelectEqual ? (byte) 1 : (byte) 0);
                dest.writeList(this.mChildLayoutBeans);
            }

            public LayoutBean() {
            }

            protected LayoutBean(Parcel in) {
                this.layoutType = in.readInt();
                this.layoutTitle = in.readString();
                this.showRightMenuType = in.readInt();
                this.startNumber = in.readInt();
                this.childItemCount = in.readInt();
                this.itemType = in.readInt();
                this.selectMin = in.readInt();
                this.selectMax = in.readInt();
                this.splitCharacter = in.readString();
                this.isSelectEqual = in.readByte() != 0;
                this.mChildLayoutBeans = new ArrayList<ChildLayoutBean>();
                in.readList(this.mChildLayoutBeans, ChildLayoutBean.class.getClassLoader());
            }

            public static final Creator<LayoutBean> CREATOR = new Creator<LayoutBean>() {
                @Override
                public LayoutBean createFromParcel(Parcel source) {
                    return new LayoutBean(source);
                }

                @Override
                public LayoutBean[] newArray(int size) {
                    return new LayoutBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mainType);
            dest.writeString(this.betCode);
            dest.writeString(this.playTypeName);
            dest.writeString(this.playTypeCode);
            dest.writeString(this.scheme);
            dest.writeString(this.sampleBet);
            dest.writeString(this.sampleOpen);
            dest.writeString(this.playTypeExplain);
            dest.writeString(this.singleExplain);
            dest.writeInt(this.maxWinning);
            dest.writeByte(this.isRelation ? (byte) 1 : (byte) 0);
            dest.writeList(this.mLayoutBeans);
        }

        public PlayBean() {
        }

        protected PlayBean(Parcel in) {
            this.mainType = in.readString();
            this.betCode = in.readString();
            this.playTypeName = in.readString();
            this.playTypeCode = in.readString();
            this.scheme = in.readString();
            this.sampleBet = in.readString();
            this.sampleOpen = in.readString();
            this.playTypeExplain = in.readString();
            this.singleExplain = in.readString();
            this.maxWinning = in.readInt();
            this.isRelation = in.readByte() != 0;
            this.mLayoutBeans = new ArrayList<LayoutBean>();
            in.readList(this.mLayoutBeans, LayoutBean.class.getClassLoader());
        }

        public static final Creator<PlayBean> CREATOR = new Creator<PlayBean>() {
            @Override
            public PlayBean createFromParcel(Parcel source) {
                return new PlayBean(source);
            }

            @Override
            public PlayBean[] newArray(int size) {
                return new PlayBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.totalType);
        dest.writeString(this.parentTitle);
        dest.writeString(this.type);
        dest.writeList(this.mPlayBeans);
    }

    public PlayTypeBean() {
    }

    protected PlayTypeBean(Parcel in) {
        this.totalType = in.readString();
        this.parentTitle = in.readString();
        this.type = in.readString();
        this.mPlayBeans = new ArrayList<PlayBean>();
        in.readList(this.mPlayBeans, PlayBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PlayTypeBean> CREATOR = new Parcelable.Creator<PlayTypeBean>() {
        @Override
        public PlayTypeBean createFromParcel(Parcel source) {
            return new PlayTypeBean(source);
        }

        @Override
        public PlayTypeBean[] newArray(int size) {
            return new PlayTypeBean[size];
        }
    };
}

