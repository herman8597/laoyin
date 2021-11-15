package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupTaskMaterialService;
import com.bat.laoyin.api.makeup.dto.MakeupTaskMaterialQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupTaskMaterialDTO;
import com.bat.laoyin.dao.makeup.MakeupTaskMaterialMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskMaterialDO;
import com.bat.laoyin.service.makeup.executor.MakeupTaskMaterialCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupTaskMaterialQryExe;

/**
 * <p>
 * 拼版任务与物料关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
public class MakeupTaskMaterialServiceImpl extends ServiceImpl<MakeupTaskMaterialMapper, MakeupTaskMaterialDO>
    implements MakeupTaskMaterialService {

    @Resource
    private MakeupTaskMaterialCmdExe cmdExe;

    @Resource
    private MakeupTaskMaterialQryExe qryExe;

    public MakeupTaskMaterialServiceImpl(@Autowired MakeupTaskMaterialCmdExe serviceCmd,
        @Autowired MakeupTaskMaterialQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupTaskMaterialDTO> selectPageVo(IPage<MakeupTaskMaterialDO> page, MakeupTaskMaterialQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupTaskMaterialDO> selectVo(MakeupTaskMaterialQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public List<MakeupTaskMaterialDTO> listOrderMaterial(MakeupTaskMaterialQry qry) {
        return qryExe.listOrderDetail(qry);
    }

}