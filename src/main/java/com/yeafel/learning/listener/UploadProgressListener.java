package com.yeafel.learning.listener;

import com.yeafel.learning.dataobject.Progress;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by kangyifan on 2018/11/13 10:47
 */
@Component
public class UploadProgressListener implements ProgressListener {

    private HttpSession session;

    public void setSession(HttpSession session){
        this.session = session;
        Progress p = new Progress();
        session.setAttribute("uploadStatus",p);
    }


    /**
     *  设置当前已读取文件的进度
     * @param readBytes 已读的文件大小（单位byte）
     * @param allBytes 文件总大小（单位byte）
     * @param index 正在读取的文件序列
     */
    @Override
    public void update(long readBytes, long allBytes, int index) {
        Progress p = (Progress) session.getAttribute("uploadStatus");
        p.setReadBytes(readBytes);
        p.setAllBytes(allBytes);
        p.setCurrentIndex(index);
    }
}
