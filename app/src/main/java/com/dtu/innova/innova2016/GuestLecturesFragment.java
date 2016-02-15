package com.dtu.innova.innova2016;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arpit on 2/14/2016.
 */
public class GuestLecturesFragment extends Fragment {
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.tabs)
    TabLayout tabs;
    PagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_scrolling, null);
        ButterKnife.bind(getActivity());
        CharSequence[] titles = {"Dr. A.S. Pillai", "Diwakar Vaish", "Shiv Khera", "Arjun Vajpai"};
        CharSequence[] descriptions = {
                    "Dr A. Sivathanu Pillai is the father of BrahMos, world's fastest cruise missile in operation. He is also the founder-CEO and managing director of the BrahMos Aerospace Private Limited. Dr A.S. Pillai has served as the programme director for Agni, Prithvi, Nag and Akash missiles and has immensely contributed to India's PSLV and GSLV programs under Dr APJ Abdul Kalam and Dr Satish Dhawan. " +
                        "For his contribution to India's defence and space programs, he was conferred the Padma Bhushan, India's third " +
                        "highest civilian award, in 2013.\n\n\n" +
                            "20th January 2016, B.R. Ambedkar Auditorium",
                    "Mr Vaish has successfully conducted the tests for \"Human Brain Cloning\" recently." +
                            "Mr Vaish is also the creator of India's first mind controlled robots and was lauded " +
                            "worldwide for his work. In his early years, Mr Vaish created India's first fully-intelligent " +
                            "autonomous football playing robots and is also credited with making India's first intelligent " +
                            "dancing robot, which could also do push ups, split legs among other moves!" +
                            "Do not miss the chance to meet Mr Vaish, who will speak about Human Brain Cloning at " +
                            "INNOVA 2016 GUEST LECTURES\n\n\n" +
                            "20th January 2016, B.R. Ambedkar Auditorium",
                    "Shiv Khera is an Indian author of self-help books, including You Can Win, " +
                            "and an activist. He launched a movement against caste-based reservation in India, founded an " +
                            "organization called Country First Foundation, and started the Bhartiya Rashtravadi Samanta Party.\n\n\n" +
                            "28th January, B.R. Ambedkar Auditorium",
                    "Arjun Vajpai is the third youngest Indian to climb Mount Everest. He achieved this feat at an age of 16 years, 11 months and 18 days." +
                            " He broke the record set by Krushnaa Patil of Maharashtra who climbed the summit at the age of 19. " +
                            "On 20 May 2011, he became the youngest climber ever to summit Lhotse, aged 17 years, 11 months and 16 days. " +
                            "Arjun also became the youngest to summit Mt.Manaslu on October 4, 2011 at 10 am.\n\n\n" +
                            "2 P.M., 2nd February, B.R. Ambedkar Auditorium"
                };
        int posters[] = {R.drawable.lecture1, R.drawable.lecture2, R.drawable.lecture3, R.drawable.lecture4};
        adapter = new PagerAdapter(this.getChildFragmentManager(), titles, descriptions, posters);
        if(pager == null) {
            pager = (ViewPager) view.findViewById(R.id.pager);
            pager.setAdapter(adapter);
        }
        if(tabs == null)
            tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
        return view;
    }
}