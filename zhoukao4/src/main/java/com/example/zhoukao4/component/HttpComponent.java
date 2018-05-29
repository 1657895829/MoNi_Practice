package com.example.zhoukao4.component;

import com.example.zhoukao4.module.HttpModule;
import com.example.zhoukao4.ui.fragment.NewsFragment;

import javax.inject.Inject;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void Inject(NewsFragment newsFragment);
}
