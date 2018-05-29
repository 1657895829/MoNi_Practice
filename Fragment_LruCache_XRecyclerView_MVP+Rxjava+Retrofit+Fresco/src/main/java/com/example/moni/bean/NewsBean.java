package com.example.moni.bean;

import java.util.List;

/**
 * Created   by   Dewey .
 * http://api.tianapi.com/mobile/?key=71e58b5b2f930eaf1f937407acde08fe&num=10
 */

public class NewsBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2018-05-28 17:28","title":"抖音温婉被封杀：17岁辍学、整容、私生活混乱","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/eeeaffb321674aeeb3ccdc77a9a69d4220180528172741.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/17/DITLKQB000097U7R.html"},{"ctime":"2018-05-28 16:17","title":"腾讯QQ影音或被取消：官网已无法访问 2年没更新","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/2018/05/28/11d4e45f9a4c475391f44f4c3e15f863.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/16/DITHIKVP00097U7R.html"},{"ctime":"2018-05-28 14:58","title":"差评宣布主动退还腾讯投资 称将完成版权规范","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/6c5b21e23e444ed1809f543c57c779ec20180528145721.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/14/DITD1D4D00097U7R.html"},{"ctime":"2018-05-28 15:35","title":"花椒宣布推出PC端直播产品 \u201c猫啵\u201d","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/3ebdd2c8a3a3456199b18e7d8b45604220180528153550.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/15/DITF5UDJ00097U7R.html"},{"ctime":"2018-05-28 10:12","title":"马化腾：发达国家芯片AI领先，数字化或秒超中国","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/2018/05/28/a7a58342de1b4b86ad7be3d92dfb8cc7.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/10/DISSMI1100097U7R.html"},{"ctime":"2018-05-28 10:54","title":"马化腾：我们腾讯不想抢别人饭碗，只想做助手","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/569307ebeb2d420a9bd15f5624c922e520180528105159.jpeg?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/10/DISV249M00097U7R.html"},{"ctime":"2018-05-28 09:44","title":"华为胡厚：在全球数字化浪潮中 欧洲不进则退","description":"通信行业","picUrl":"http://cms-bucket.nosdn.127.net/34656890137b4054ba6a6e74a874150820180528094417.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/09/DISR2H6200097U7S.html"},{"ctime":"2018-05-28 09:09","title":"业内人士爆料：百度市场公关副总裁王路将离职","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/09868c0ed5ff4995baad8eb61d217f7320180528090918.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/09/DISP2E6000097U7R.html"},{"ctime":"2018-05-28 08:02","title":"媒体批腾讯：垄断思维该换换了 应重新审视价值","description":"移动互联","picUrl":"http://cms-bucket.nosdn.127.net/2fba8b3383a24f0ba914c4dcb765110020180528080137.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/08/DISL763200097U7R.html"},{"ctime":"2018-05-28 07:55","title":"5G标准是如何制定的，中国企业话语权究竟如何","description":"通信行业","picUrl":"http://cms-bucket.nosdn.127.net/2018/05/28/34bd25a70f2f4d2ca2ca71638e8135bb.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0528/07/DISKQP5600097U7S.html"}]
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

    public static class NewslistBean {
        /**
         * ctime : 2018-05-28 17:28
         * title : 抖音温婉被封杀：17岁辍学、整容、私生活混乱
         * description : 移动互联
         * picUrl : http://cms-bucket.nosdn.127.net/eeeaffb321674aeeb3ccdc77a9a69d4220180528172741.png?imageView&thumbnail=200y140
         * url : http://tech.163.com/18/0528/17/DITLKQB000097U7R.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

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
    }
}
