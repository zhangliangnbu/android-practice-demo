package com.liang.androidskilldemo.creating;

import android.os.Handler;
import android.os.Message;

/**
 * created by zhangliang on 2021/6/28
 * for creating
 *
 */
public class Laiyinhate {

    /*
     * Set this flag to true to detect anonymous, local or member classes
     * that extend this Handler class and that are not static. These kind
     * of classes can potentially create leaks.
     */
    private static final boolean FIND_POTENTIAL_LEAKS = false;
    private static final String TAG = "Handler";
    private static Handler MAIN_THREAD_HANDLER = null;


    /**
     * Subclasses must implement this to receive messages.
     */
    public void handleMessage(Message msg) {
    }

    /**
     * 异步消息不受同步障碍的限制。它们通常表示中断、输入事件和其他必须独立处理的信号，即使其他工作已经挂起。
     * 请注意，尽管异步消息之间总是按顺序传递，但与同步消息相比，异步消息的传递可能是无序的。
     * 如果这些消息的相对顺序很重要，那么它们可能一开始就不应该是异步的。谨慎使用。
     * 调度消息是通过post、postAtTime(Runnable, long)、postDelayed、sendEmptyMessage、sendMessage、sendMessageAtTime
     * 和sendMessageDelayed方法来完成的。post版本允许您将接收到的Runnable对象放入队列
     * 三种：
     * 很好，加油，跟随哈大人，前途不可限量。
     * 什么？你当众说哈大人要造反？完犊子了，我们家要遭难，怎么办？大家赶紧收拾下，离开帝国！
     * 你既然已经表明态度了，咱们家族要好好考虑下后面的行动，是全力支持哈大人还是两头下注，或者鹬蚌相争渔翁得利，难道咱们家族就不能成为帝国的主宰？
     * 缑：前几天我问了你两个问题，关于莱因哈特答复维斯来祝福他夺取天下的，而你当时只回答了第一个，现在是否有时间回答第二个呢？
     * 我：有第二个问题？具体如何？
     * 缑：第二个问题是，假设你是维斯来的家人，你听到维斯来的话后会怎么说？
     * 我：你是说当维斯来的家人听到维斯来转述莱因哈特的话后，他的家人会说什么话？
     * 缑：对的。
     * 我：莱因哈特说了啥？有点忘记了。
     * 缑：哦，我把他们的对话再说一遍吧：
     * 维斯来的祝福是：“吾皇陛下哟，请赢得这场胜利，取得天下吧！” 莱因哈特答道:“小伙子，好样的，我会赢得这场战斗的胜利，到时请你自豪的告诉你的家人，是你，让我赢得了这场战争！”
     * 我：嗯...这个不难回答。这里我给出几个可能性较大的答案。
     *      可能回答一，“小来，你要继续加油。哈大人说的是客气话，是鼓励你要更加努力才行！”
     * 一个稍微明智的父母怎么会相信一个二等兵能成为一场战争的决定因素呢？这个回答可能性最大。
     *     可能回答二，“什么，那场战争的胜利中，你才是最大的英雄啊。孩子，你真了不起！” 对孩子的能力过于信任的父母也许会作出这样的回答。而且这种回答往往会演变为第三种回答。
     *     可能回答三，“孩子，你才是最大的英雄啊！可是......你为什么还是二等兵而没有升为将军呢？一定是哈大人赏罚不公，这世道也太黑了。” 自己的孩子永远是对的，永远是最好的，当事情不符合自己的认知的时候，那一定是外面的问题。这一类巨婴式的父母，但愿世间越来越少。
     * 缑：回答完了吗？
     * 我：......算是回答完了吧。当然还有其他可能的回答，比如与子女关系紧张的父母或不善言语的父母，其回答可能是一个眼神，或者一个字“嗯”或“好”。
     * 另外可能还有一些清心寡欲或通达的父母并不希望自己的子女劳心劳力地去建功立业，他们应该会说出其他的一些话。
     * 算了，就不继续往下假设猜测了。世间父母的性情多种多样，但大部分父母的回答应该是第一个。好了，回答完毕。
     * 缑：你这次的回答似乎没有上次回答得好，太懒洋洋了。
     * 我：可能是......天气太热的缘故吧。有研究证明，温度太高时，人的思维运行速度就会变慢。你不信，诶诶，你不要走啊，这可是我的最新研究成成果哎。
     * ---
     * 哲学改变的问题
     *
     * 肯定，
     * 导致循环方法终止，而不处理消息队列中的任何更多消息。
     * 任何在环形器被要求退出后向队列发送消息的尝试都将失败。例如，Handler.sendMessage(Message)方法将返回false。
     * 使用此方法可能是不安全的，因为某些消息可能在活套终止之前无法传递。考虑使用quitSafely代替，以确保所有待处理的工作都以有序的方式完成。
     *
     */
    public Laiyinhate() {

    }

    /**
     * 默认FragmentFactory使用的构造函数。
     * 如果你想使用一个非默认的构造函数来确保你的构造函数在片段被重新实例化时被调用，你必须设置一个自定义FragmentFactory。
     * 强烈建议使用setararguments提供参数，然后通过片段使用getararguments进行检索。这些参数将自动保存并与Fragment一起恢复。
     * 应用程序通常不应该实现构造函数。喜欢onAttach(上下文)。
     * 它是应用程序代码可以运行的第一个地方，在那里片段已经准备好被使用——片段实际上与它的上下文相关联。
     * 一些应用程序可能还希望实现onInflate来从布局资源中检索属性，尽管注意这是在片段附加时发生的。
     */
    public void defaultFragmentFactory() {
    }

    /**
     * Callback interface you can use when instantiating a Handler to avoid
     * having to implement your own subclass of Handler.
     */
    public interface Callback {
        /**
         * @param msg A {@link android.os.Message Message} object
         * @return True if no further handling is desired
         */
        boolean handleMessage(Message msg);
    }










}
