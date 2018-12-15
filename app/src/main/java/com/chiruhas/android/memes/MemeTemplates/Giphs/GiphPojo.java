package com.chiruhas.android.memes.MemeTemplates.Giphs;

import java.util.List;

public class GiphPojo {
    private List<Data> dataList;
    String title;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GiphPojo() {
    }

    public GiphPojo(List<Data> dataList, String title) {

        this.dataList = dataList;
        this.title = title;
    }

    public class Data
    {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBitly_gif_url() {
            return bitly_gif_url;
        }

        public void setBitly_gif_url(String bitly_gif_url) {
            this.bitly_gif_url = bitly_gif_url;
        }

        public String getBitly_url() {
            return bitly_url;
        }

        public void setBitly_url(String bitly_url) {
            this.bitly_url = bitly_url;
        }

        public String getEmbed_url() {
            return embed_url;
        }

        public void setEmbed_url(String embed_url) {
            this.embed_url = embed_url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        private String type,id,slug,url,bitly_gif_url,bitly_url,embed_url,username,source;
        private Images images;


        public class Images
        {
            ImageModels fixed_height,fixed_width;
            Size downsized,downsized_large;

            public ImageModels getFixed_height() {
                return fixed_height;
            }

            public void setFixed_height(ImageModels fixed_height) {
                this.fixed_height = fixed_height;
            }

            public ImageModels getFixed_width() {
                return fixed_width;
            }

            public void setFixed_width(ImageModels fixed_width) {
                this.fixed_width = fixed_width;
            }

            public Size getDownsized() {
                return downsized;
            }

            public void setDownsized(Size downsized) {
                this.downsized = downsized;
            }

            public Size getDownsized_large() {
                return downsized_large;
            }

            public void setDownsized_large(Size downsized_large) {
                this.downsized_large = downsized_large;
            }

            public class ImageModels
            {
                String url,width,height,size,mp4_size,webp,webp_size;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getMp4_size() {
                    return mp4_size;
                }

                public void setMp4_size(String mp4_size) {
                    this.mp4_size = mp4_size;
                }

                public String getWebp() {
                    return webp;
                }

                public void setWebp(String webp) {
                    this.webp = webp;
                }

                public String getWebp_size() {
                    return webp_size;
                }

                public void setWebp_size(String webp_size) {
                    this.webp_size = webp_size;
                }
            }
            public class Size
            {

                String url,width,height,size;

                public String getUrl() {
                    return url;
                }

                public String getWidth() {
                    return width;
                }

                public String getHeight() {
                    return height;
                }

                public String getSize() {
                    return size;
                }
            }
        }
    }
}
