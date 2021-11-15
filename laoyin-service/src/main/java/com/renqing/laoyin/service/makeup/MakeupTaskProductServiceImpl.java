package com.bat.laoyin.service.makeup;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.impl.ServiceImpl;
import com.bat.laoyin.api.makeup.MakeupTaskProductService;
import com.bat.laoyin.api.makeup.dto.MakeupTaskProductQry;
import com.bat.laoyin.dao.makeup.MakeupTaskProductMapper;
import com.bat.laoyin.dao.makeup.dataobject.MakeupTaskProductDO;
import com.bat.laoyin.service.makeup.executor.MakeupTaskProductCmdExe;
import com.bat.laoyin.service.makeup.executor.MakeupTaskProductQryExe;

/**
 * <p>
 * 拼版任务与产品关联表 服务实现类
 * </p>
 *
 * @author lim
 * @since 2021-09-16
 */
@Service
public class MakeupTaskProductServiceImpl extends ServiceImpl<MakeupTaskProductMapper, MakeupTaskProductDO>
    implements MakeupTaskProductService {

    @Resource
    private MakeupTaskProductCmdExe cmdExe;

    @Resource
    private MakeupTaskProductQryExe qryExe;

    public MakeupTaskProductServiceImpl(@Autowired MakeupTaskProductCmdExe serviceCmd,
        @Autowired MakeupTaskProductQryExe serviceQry) {
        super(serviceCmd, serviceQry);
    }

    @Override
    public IPage<MakeupTaskProductDO> selectPageVo(IPage<MakeupTaskProductDO> page, MakeupTaskProductQry qry) {
        return qryExe.selectPageVo(page, qry);
    }

    @Override
    public List<MakeupTaskProductDO> selectVo(MakeupTaskProductQry qry) {
        return qryExe.selectVo(qry);
    }

}