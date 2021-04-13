package com.cibr.vectorservice.service;

import com.cibr.vectorservice.dao.CibrSysFileMapper;
import com.cibr.vectorservice.entity.CibrSysFile;
import com.cibr.vectorservice.entity.CibrSysFileExample;
import com.cibr.vectorservice.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Value("${vector.fileUploadPath}")
    private String filePath;

    @Autowired
    CibrSysFileMapper fileMapper;

    @Transactional(rollbackFor = Exception.class)
    public List<String> saveFile(List<MultipartFile> files, String detailId, String userId){
        File dist = null;
        List<String> fileIds = new ArrayList<>();
        for (MultipartFile file : files){
            if (file.isEmpty()){
                throw new RuntimeException("上传的文件为空！");
            }
            String fileName = file.getOriginalFilename();
            File path = new File(filePath);
            if (!path.exists()){
                path.mkdirs();
            }
            dist = new File(filePath + File.separator+ CommonUtil.UUID() + fileName);
            String md5 = null;
            try {
                md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
                file.transferTo(dist);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            CommonUtil.replaceSpecialChar(dist.getName());
            CibrSysFile cibrSysFile = new CibrSysFile();
            cibrSysFile.setId(CommonUtil.UUID());
            cibrSysFile.setCreater(userId);
            cibrSysFile.setCreatetime(new Date());
            cibrSysFile.setDetailid(detailId);
            cibrSysFile.setFilestatu("00");
            cibrSysFile.setMd5(md5);
            cibrSysFile.setName(CommonUtil.replaceSpecialChar(dist.getName()));
            cibrSysFile.setPath(CommonUtil.replaceSpecialChar(dist.getPath()));
            cibrSysFile.setRealname(CommonUtil.replaceSpecialChar(fileName));
            fileMapper.insert(cibrSysFile);
            fileIds.add(cibrSysFile.getId());
        }
        return fileIds;
    }


    public List<CibrSysFile> findFiles(String detailId) {
        CibrSysFileExample fileExample = new CibrSysFileExample();
        fileExample.createCriteria().andDetailidEqualTo(detailId);
        List<CibrSysFile> files = fileMapper.selectByExample(fileExample);
        if (files == null){
            return  null;
        }
        return files;
    }

    public File getFileById(String fileId) {
        CibrSysFile file = fileMapper.selectByPrimaryKey(fileId);
        File dbfile = new File(file.getPath());
        return dbfile;
    }
}
