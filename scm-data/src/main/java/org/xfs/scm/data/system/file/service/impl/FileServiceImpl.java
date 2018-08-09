package org.xfs.scm.data.system.file.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.system.file.mapper.FileMapper;
import org.xfs.scm.data.system.file.po.FilePo;
import org.xfs.scm.data.system.file.service.FileServiceI;
import org.xfs.scm.data.system.file.vo.FileVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileServiceI {

    @Resource
    private FileMapper fileMapper;

    @Override
    public int removeFile(FileVo vo) {
        return this.fileMapper.removeFile(vo);
    }

    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    @Override
    public int addFile(FileVo vo) {
        return this.fileMapper.addFile(vo);
    }
    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    @Override
    public int addFileList(List<FileVo> list) {
        return this.fileMapper.addFiles(list);
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<FilePo> getFiels(FileVo vo) {
        return this.fileMapper.getFiles(vo);
    }

    @Override
    public FilePo getFile(FileVo vo) {
        if(vo!=null){
            List<FilePo> list=this.getFiels(vo);
            if(list!=null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Grid<FilePo> grid(FileVo vo, Integer page, Integer rows) {
        Grid<FilePo>grid=new Grid<>();
        Page<FilePo> pages=PageHelper.startPage(page,rows );
        List<FilePo>list=this.getFiels(vo);
        if(!list.isEmpty()){
            grid.setTotal(pages.getTotal());
            grid.setRows(list);
        }
        return grid;
    }

    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    @Override
    public int modifyFile(FileVo vo) {
        return this.fileMapper.modifyFile(vo);
    }

    @Override
    public int updateFileState(FileVo record) {
        return this.fileMapper.updateFileState(record);
    }
}
