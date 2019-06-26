package com.zr.dubbo;

import java.util.List;

import com.zr.csi.model.Notice;

public interface NoticeDubboService {

	List<Notice> queryNotice(String title, String content);

	int addNotice(Notice notice);

	int updateNotice(Notice notice);

	int deleteNotice(Integer id);
}
