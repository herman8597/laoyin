package com.bat.laoyin.api.goods;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.rq.service.IServiceCmd;
import com.baomidou.mybatisplus.extension.rq.service.IServiceQry;
import com.bat.laoyin.api.goods.dto.GoodsQry;
import com.bat.laoyin.api.goods.dto.data.GoodsDTO;
import com.bat.laoyin.dao.goods.dataobject.GoodsDO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lim
 * @since 2021-09-09
 */
public interface GoodsService extends IServiceCmd<GoodsDO>, IServiceQry<GoodsDO> {

    IPage<GoodsDO> selectPageVo(IPage<GoodsDO> page, GoodsQry qry);

    List<GoodsDO> selectVo(GoodsQry qry);

    IPage<GoodsDTO> listMatch(IPage<GoodsDO> page, GoodsQry qry);

    void mySave(GoodsDO aDo);
}
