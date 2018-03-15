package com.zfy.rxbestpractices.http.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: fanyuzeng on 2018/3/1 14:53
 */
public class WeChatBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2018-03-01","title":"3月新一轮流感爆发？专家怎么说？这样做可以预防流感！","description":"新闻夜航","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62704985.static/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5OTgxMjA4MA==&idx=3&mid=2651841942&sn=4d57c991bc1cde3333b437433cf0e9be"},{"ctime":"2018-03-01","title":"\u201c爸妈离婚后，把我从四楼推下去了\u2026\u2026\u201d","description":"遇见番茄","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62802113.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzIwMjQzMDQ5Mg==&idx=1&mid=2247495009&sn=04d01a56a40f6e1d973d0107d4ddcc2d"},{"ctime":"2018-03-01","title":"高中就拒绝CBA千万合同！恐怖天赋让父母为他投100万美元的保险！","description":"准者篮球训练营","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62351431.static/640","url":"https://mp.weixin.qq.com/s?__biz=MzA3MTQ4NjM3MQ==&idx=1&mid=2650626413&sn=09516f379d9be38b936eea2e794862fe"},{"ctime":"2018-03-01","title":"女子返程途中猝死车内\u2026这样的错误很多人都犯！","description":"十堰晚报","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62802082.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzA5NjM4MDkyNQ==&idx=4&mid=2653214254&sn=0e53395a1e4cf9cfb88f3e3a7436dbe3"},{"ctime":"2018-03-01","title":"EDG连续两局被零封 Joker、米勒都看不下去了","description":"腾讯游戏频道","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62802072.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5MzAzMDUwMA==&idx=1&mid=2653321109&sn=4ebd8a733c2c1a70f81b67bcf72af1a4"},{"ctime":"2018-03-01","title":"西安一幼儿园家长抢新生名额摆凳子排长队 西安教育问政在即 这是摆现行？","description":"陕西都市快报","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62299666.static/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5MTMwNjk0MQ==&idx=1&mid=2652807722&sn=f6ed0ef772141fbad4b4874a17756ce9"},{"ctime":"2018-03-01","title":"真正高情商的人，都很会骂人","description":"谈心社","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62802151.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzAwNjgyMjA1MA==&idx=1&mid=2650162274&sn=b92a7f248f3252af32608a814df76ce7"},{"ctime":"2018-03-01","title":"混得再不好，也要参加同学会","description":"槽值","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62802199.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzIzNDAxNjkxOA==&idx=5&mid=2650583598&sn=3f3c93df9a0bff49e4c7d89e3df862e8"},{"ctime":"2018-03-01","title":"刚刚南京落户新政细则出炉！4种方法都能落户！今天开始执行！","description":"南京楼市","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62801972.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5ODkyNzc0MQ==&idx=1&mid=2650029297&sn=4660bcdd5adbac603f47359509fd635d"},{"ctime":"2018-03-01","title":"和小朋友们聊聊爱情是什么，嗯...纯洁之中透着小八卦","description":"这里是美国","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-16517882.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5NDE1MDYyMQ==&idx=4&mid=2651024079&sn=c064a2512145e0b906b122d32609fa50"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }


    public static class NewslistBean implements MultiItemEntity {
        /**
         * ctime : 2018-03-01
         * title : 3月新一轮流感爆发？专家怎么说？这样做可以预防流感！
         * description : 新闻夜航
         * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-62704985.static/640
         * url : https://mp.weixin.qq.com/s?__biz=MjM5OTgxMjA4MA==&idx=3&mid=2651841942&sn=4d57c991bc1cde3333b437433cf0e9be
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public static final int ITEM_TYPE_BIG = 1;
        public static final int ITEM_TYPE_SMALL = 0;
        private int itemType = ITEM_TYPE_SMALL;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "ctime='" + ctime + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeChatBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }
}
