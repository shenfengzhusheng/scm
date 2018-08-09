package org.xfs.scm.data.system.file.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.file.po.FilePo;
import org.xfs.scm.data.system.file.vo.FileVo;

import java.util.List;

public interface FileServiceI {
    int removeFile(FileVo vo);

    int addFile(FileVo vo);

    int addFileList(List<FileVo> list);

    List<FilePo> getFiels(FileVo vo);

    FilePo getFile(FileVo vo);

    Grid<FilePo> grid(FileVo vo, Integer page, Integer rows);

    int modifyFile(FileVo vo);

    /**
     * 修改文件状态
     * @param record
     * @return
     */
    int updateFileState(FileVo record);

}
