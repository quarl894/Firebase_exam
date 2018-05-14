package youngjung.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

public class finanActivity extends baseActivity {
    TextView tv_title1, tv_title2, tv_content, tv_step;
    ImageView img_fnan;
    int[] arr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finan);

        tv_title1 = findViewById(R.id.tv_title1);
        tv_title2 = findViewById(R.id.tv_title2);
        tv_content = findViewById(R.id.tv_content);
        tv_step = findViewById(R.id.tv_step_title);

        img_fnan = findViewById(R.id.img_finan);
        arr = new int[2];

        String[] title = new String[12];
        String[] title2 = new String[12];
        title[0] = "자동이체를 걸어서 매달 지출 전 먼저 돈이 빠져나가게 해요!";
        title[1] = "최고의 재테크, 미니멀리즘!";
        title[2] = "통장 쪼개기!";

        title2[0] = "통장잔고사 0원일 때까지 써야 직성이 풀린다구요?<br> 선저축 후지출로 소비와 지출 패턴을 바꿔보는 건 어떠세요?<br><br> <strong>선저축 후지출이란!</strong><br> 목표를 달성하기 위한 저축금액을 도출해서 매월 급여일에 자동이체가  후, 나머지 금액으로 지출하는 방법이예요.<br><br> 오늘 부터 일상 속 불필요한 소비부터 하나씩 차근차근 줄여보는 건 어떨까요?";
        title2[1] = "나를 알고 소비를 하는 미니멀리즘을 실천해봐요!";
        title2[2] = "‘고정지출’과 ‘변동지출’에 따라 통장을 쪼개보세요!";

        String[] content = new String[12];
        content[0] = "통장잔고사 0원일 때까지 써야 직성이 풀린다구요?<br> 선저축 후지출로 소비와 지출 패턴을 바꿔보는 건 어떠세요?<br><br> <strong>선저축 후지출이란!</strong><br> 목표를 달성하기 위한 저축금액을 도출해서 매월 급여일에 자동이체가  후, 나머지 금액으로 지출하는 방법이예요.<br><br> 오늘 부터 <strong>일상 속 불필요한 소비</strong>부터 하나씩 차근차근 줄여보는 건 어떨까요?";
        content[1] = "우리는 자본주의, 물질만능주의가 최고조에 이른 사회에서 살고 있어요. 옷 시장도 옛날에는 4시즌이었지만 요즘에는 52시즌이라고 해요.<br><br> 무엇인가를 사고 싶어서 느껴지는 가난은 불행하지만 <strong>자발적인 가난은 행복하고 삶</strong>에 여유를 줘요.<br><br> 금리가 낮고 투자가 두려운 요즘, <strong>자신을 알고 소비를 하는 미니멀리즘</strong>을 실천해보는 건 어떠세요?<br>";
        content[2] = "귀찮아서 통장 한개에 모든 돈을 넣어놓으시진 않았나요?<br> 돈이 들어오기 직전에 보면 항상 바닥이 보이진 않나요?<br> 그럼, 통장 쪼개기에 집중하세요!<br><br> 금리가 낮아 <strong>월급이나 용돈, 고정지출, 비상금 통장</strong>으로 나누어 관리하는 통장 쪼개기가 재조명을 받고 있어요.<br><br> 돈이 생기면 고정지출이 되는 것은 보내고 월말에 모든 지출을 하고 남은 돈은 비상통장에 저금해보세요.";
        Intent i = getIntent();
        arr = i.getExtras().getIntArray("send");
        tv_content.setText(Html.fromHtml(content[0]));
        switch(arr[0]){
            case 0 :
                if(arr[1]==1){
                    tv_content.setText(Html.fromHtml(content[1]));
                    tv_step.setText("STEP"+(arr[1]+1));
                    tv_title1.setText(Html.fromHtml(title[1]));
                    tv_title2.setText(Html.fromHtml(title2[1]));
                    img_fnan.setImageResource(R.drawable.group);
                }else if(arr[1]==2){
                    tv_content.setText(Html.fromHtml(content[2]));
                    tv_step.setText("STEP"+(arr[1]+1));
                    tv_title1.setText(Html.fromHtml(title[2]));
                    tv_title2.setText(Html.fromHtml(title2[2]));
                    img_fnan.setImageResource(R.drawable.group_2);
                }
                break;
        }
    }
}
