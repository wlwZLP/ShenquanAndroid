package com.dongdian.shenquan.bean;

import java.util.List;

public class CategoriesBean {

    /**
     * id : 1
     * name : 女装
     * sname : 女装
     * level : 0
     * parent_id : 0
     * logo : null
     * children : [{"id":56,"name":"女裙","sname":"女裙","level":1,"parent_id":1,"logo":null,"children":[{"id":59,"name":"连衣裙","sname":"连衣裙","level":2,"parent_id":56,"logo":null,"children":[]},{"id":60,"name":"半身裙","sname":"半身裙","level":2,"parent_id":56,"logo":null,"children":[]},{"id":61,"name":"旗袍","sname":"旗袍","level":2,"parent_id":56,"logo":null,"children":[]}]}]
     */

    private int id;
    private String name;
    private String sname;
    private String level;
    private String parent_id;
    private String logo;
    private List<ChildrenBeanX> children;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * id : 56
         * name : 女裙
         * sname : 女裙
         * level : 1
         * parent_id : 1
         * logo : null
         * children : [{"id":59,"name":"连衣裙","sname":"连衣裙","level":2,"parent_id":56,"logo":null,"children":[]},{"id":60,"name":"半身裙","sname":"半身裙","level":2,"parent_id":56,"logo":null,"children":[]},{"id":61,"name":"旗袍","sname":"旗袍","level":2,"parent_id":56,"logo":null,"children":[]}]
         */

        private int id;
        private String name;
        private String sname;
        private int level;
        private int parent_id;
        private Object logo;
        private List<ChildrenBean> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 59
             * name : 连衣裙
             * sname : 连衣裙
             * level : 2
             * parent_id : 56
             * logo : null
             * children : []
             */

            private int id;
            private String name;
            private String sname;
            private int level;
            private int parent_id;
            private String logo;
            private List<?> children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
