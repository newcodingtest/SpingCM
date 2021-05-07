package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

  private Long rno;
  private Long bno;

  private String reply;
  private String replayer;
  private Date replyDate;
  private Date updateDate;

}
