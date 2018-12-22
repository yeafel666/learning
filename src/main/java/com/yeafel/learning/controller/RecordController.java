package com.yeafel.learning.controller;

import com.yeafel.learning.dto.RecordDTO;
import com.yeafel.learning.dto.UserDTO;
import com.yeafel.learning.service.RecordService;
import com.yeafel.learning.utils.ResultVOUtil;
import com.yeafel.learning.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author Yeafel
 * 2018/11/24 17:48
 * Do or Die，To be a better man!
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;


    @GetMapping("/list")
    public ResultVO<List<RecordDTO>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                          RecordDTO recordDTO, HttpSession session){
        // HttpServletResponse response)
        UserDTO userDTO = (UserDTO) session.getAttribute("user");


        recordDTO.setUserId(userDTO.getUserId());



        Integer count = recordService.countRecordsByUserId(recordDTO.getUserId());


        PageRequest request = PageRequest.of(page-1,limit);
        Page<RecordDTO> recordDTOPage = recordService.findRecordsByUserId(recordDTO.getUserId(),request);

//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(recordDTOPage.getContent(),count);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems, Long id){
        /*判断时单行删除还是批量删除*/
        if (delitems != null) {
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                recordService.deleteByRecordId(a);
            }
        } else {
            recordService.deleteByRecordId(id);
        }
        return ResultVOUtil.success();
    }

}
