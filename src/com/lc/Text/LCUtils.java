package com.lc.Text;

public class LCUtils {

    /**
     * 正则校验手机号
     */
    public static boolean checkPhone(String tel){
        if(tel.matches("[0-9]{11}")){
            return true;
        }
        return  false;
    }

    /**
     * 正则校验邮件地址
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        if(email.matches("([0-9a-zA-Z]+)[@]([0-9a-zA-Z]+)[.][A-Za-z]{0,3}")){
            return true;
        }
        return false;
    }

    /**
     * 确定字符串是否有重复出现
     * @param iniString
     * @return
     *
     *
     * ：代表任意一个字符
     * .*：代表任意一个字符后面有0个或多个字符
     * (.)：选择字符中任意一个字符进行复制 和后面的(.*\\1)结合进行判断是否后面存在一个字符与它相同
     * (.)(.*\\1)：匹配案例 a*a（即首尾相同的abcsa）
     * (.)(.*\\1).*：匹配案例 a*a*
     * .*(.)(.*\\1)：匹配案例 *a*a
     * .*(.)(.*\\1).*：匹配案例 *a*a*
     */
    public boolean checkDifferent(String iniString) {
        // write code here
        return !iniString.matches(".*(.)(.*\\1).*");
    }

    public String reverseString(String iniString) {
        // write code here

        return reverse(iniString, 0, iniString.length()-1);
    }
    public String reverse(String iniString, int start, int end){
        while(start < end){
            char temp = iniString.charAt(start);
            char tempEnd = iniString.charAt(end);
            if(start == 0){
                iniString = tempEnd + iniString.substring(start+1, end)+temp;
            }else{
                iniString = iniString.substring(0, start)+tempEnd+iniString.substring(start+1, end) + temp + iniString.substring(end+1, iniString.length());
            }
            start++;
            end--;
        }
        return iniString;
    }

    public static void main(String[] args) {
        System.out.print(new LCUtils().reverseString("qckzfutq|ws}ejpl~oapz{}eldeedlj{fc}qx~zyjwb|}qkbdxouhb~yobmox~cz~dv}xbrrorgekiveqtkiohldybkpada}l|tvs|huzhvhbdzceoggustjojer{l|acbraylejy}kz|crawmjvyvq{emokxwh}cpyhhpuuz{iqhzpss{ysc~efuljgtc{ynqolagayo}xm}gos{dhs~cnnfpy{buhwaqfprhyyrlcjnxafilkifqb|jhiimjljg}hlhoxicxpzx}|zrbrcnirgjffsmv{et}hshlkhpeydaulawdeincawwfoqiknotjrlqm{lbikxoevqoxceuzokqjzxlreqqumkxdvantiwv~tuuimizkzjztyubawrxk{npsynddpkod|riyeykopvtog|||yodstyihkfkj~|vcsxupbrevrvwtnjpv}nlhtpxphiq|pevhyrnqpuwzwemzfnnwcse}b~oovstk~tqxupp~zjtsl}|j{nqzxnq|tcqxpwfgitzfjcpcglg|djepb}qabiomgqs~ltwclsgj}mjmq~fvgabufpnospuench~garubac|s|otlhquljsenx}rlmyazv{sqytm}ev~es{lsu{rtunuksgvrteunoyznzxohrp~odgmfak|~rnopn~wnkjwbleugeuzcg}nzntbvazibwnwblrzlcoseif|znmvnvs|z|prbejg}ajdcuinuhimxtmuifnb|fwdgegxrequoespxmwqkc}ow~nd|nqyfhiyzbappg}jqs~srpd|rmyrva{wbrazxwjvzz}}atcaluwbtbcicg{frooaivnjqgwba|cxgftayc{xbd~bekdbskcynhzpeugkedybvzmcibhlfvcyswak|djiagkh}qsfpd{wdgqahrawtv}|gsmihz|iik{}itku}~}huwfulcafcvizvc{hc{mer~xaj}i~uouw~jfs~jbn~tmibn~c}{xqjzwbcoyvviy|vksrtzox|ebso{h|lbpugzriasry{{a~~crdpglioddxxe{mk}zmnhm}yb{byf{germeuw~m~ahki|~amphpzrenjytn{{{px{{fegz{kul~rwf|walerubgmvuih{ctdqajmllwr{vnfnqqc~un~vhvljx}efvdrvmufyv{aey{lcupzsj}zuvmgns~|mniuqg|h{r}bnjf{qyrpftwo}ygvzacnylkpqkw}xkzjshrvuqniqtw~lxbghsdfajk}dmemv|eut~iowbxduajbtnecxfty|dm}apqjfw|ap~yuivxeybvfq~f{x~kdb{olnaoprifwl~kn}u~br}ylenrfbrc~mhpqmgq}{wzgya{sp}~qpy{{dd}~m|jrwzca|dma}eansodxobnfdq~|w~r{tj|phxvrlconnfmgmjdnowsbweqxmmjpredn|t}hdnla}}dpeisdruoxihhjrlswldt|gihgrfmrja|nwtaw{oa|mn|vbcgo{k~xneyt|wbjn}ureeepeczc|zgwgtn~gchdg|ypqgxikyhwqbenibtkktfdfigcxj~xi{~q||l}miabraflc}anls}{brdwhzddchf}ll~gitukujxrsmcqgvn|bicvlngmomhhaeipqakpgvbyggcdo|rg}fuournnt||gobhebx|knsincsudd|f|ydzjshyxfyp|j|o~}zal}kr{rcxiuq|aniednsukxe}fbsnjc}jt|ndplnakp|dpcmf~sz{bvxl~wkitaniwpbvtlpasgc|trawpfbmnwcvpqx~d{vrkut|fot|wxgqtl}xmcfiidbbqrhqm}pzox|wlfpqgsb{nx|qponrednut|pzxttmtd~gse}hmkkwv{zusfrr}vuikjszbnex~exkup{ay~kdhwxja|{z{agghgkn|hasjfdfrtc|jubkudzg|uvclpsx}xgamthmpj{p}nujpit{q|s~cgzmqvh~qccsx{btrk}jfqov~lrclblmqeiwgrcxonlekfqsea}smbt{grvtfcmryhu~oyvqdfhx}{|lolabu~rxx}|baao}ev|zorgfx|vvmelczkjorx~m|y{fuy{{{yyxq~rxuqr||drrb}petwwkayurrxoychrxzfm~{y}}pokqwg{razayeb|ksblb|revlhife{phijnfmvzjczi}ncuthptxwuthpjikepih}fkrmvaqywmb~kps{xwimuh{cx|vipkfstmahs{hmintcz}zv}tdyk}jcraj~ebk|yplseif{skt{w~minj{l|rk|mfo}bfgwsqc~mjibql"));
    }
}
