package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupSettingProductService;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductCmd;
import com.bat.laoyin.api.makeup.dto.MakeupSettingProductQry;
import com.bat.laoyin.api.makeup.dto.data.MakeupSettingProductDTO;
import com.bat.laoyin.dao.makeup.MakeupSettingProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupSettingProductDO;
import com.bat.laoyin.service.makeup.executor.MakeupSettingProductCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupSettingProductQryExe;

/**
 * <p>
 * 拼版设置与产品关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
public class MakeupSettingProductServiceImpl extends ServiceImpl<MakeupSettingProductMapper, MakeupSettingProductDO>
    implements MakeupSettingProductService {

    @Resource
    private MakeupSettingProductCmdExe cmdExe;

    @Resource
    private MakeupSettingProductQryExe qryExe;

    public MakeupSettingProductServiceImpl(@Autowired MakeupSettingProductCmdExe serviceCmd,
        @Autowired MakeupSettingProductQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupSettingProductDTO> selectPageVo(IPage<MakeupSettingProductDO> page,
        MakeupSettingProductQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupSettingProductDO> selectVo(MakeupSettingProductQry qry) {
        return qryExe.selectVo(qry);
    }

    @Override
    public void mySave(MakeupSettingProductCmd aDo) {
        cmdExe.mySave(aDo);
    }

}