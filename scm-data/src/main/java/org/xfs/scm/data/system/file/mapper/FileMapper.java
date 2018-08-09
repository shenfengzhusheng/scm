package org.xfs.scm.data.system.file.mapper;

import org.xfs.scm.data.system.file.po.FilePo;
import org.xfs.scm.data.system.file.vo.FileVo;

import java.util.List;

public interface FileMapper {
    int removeFile(FileVo record);
    int addFile(FileVo record);
    int addFiles(List<FileVo> list);
    int modifyFile(FileVo record);
    List<FilePo> getFiles(FileVo record);

    int updateFileState(FileVo record);
}
