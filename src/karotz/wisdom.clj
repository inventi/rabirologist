(ns karotz.wisdom)

(def ^:private predictions
  ["Today is not your lucky day. And I'm afraid my prediction algorithm is a pure, time-independent function."
   "Life will be as exciting as you make it. Or as boring as a three-tier architecture."
   "Don't you feel sad for the poor developer who actually sat down and wrote these predictions during his spare time?"
   "You will be leading a predictable, stable life, until one fateful day, when someone introduces uncoordinated mutable state."
   "Relax, it's all going to be fine. Unless you're a software architect, in which case there's no hope for you."
   "Next few months will be everyday business as usual. Why not try something more exciting! Like watching paint dry."
   "Using reflection in a statically typed language will be considered a bad sign. So is falling into a sinkhole."
   "Your lambda calculus is about to get a monadic facelift. Seriously, who wrote this crap?"
   "Be careful when responding to emails asking for your online banking password. Also, stop reading Facebook."
   "Reading a book will make you smarter. Why not start with Structure and Interpretation of Computer Programs?"
   "You are a disposable human resource. Quick! Learn and start using some obscure framework to ensure job security."
   "Your job spot is secure for the rest of your life. No human being is going to maintain what you coded back then."
   "You will need a pair of glasses after you start coding on that shiny new ultra high definition display."
   "You will grow old before that enterprise-grade application framework accepts your pull request."])

(defn- prediction-index [s]
  (mod (.hashCode (reverse (.toLowerCase s))) (count predictions)))

(defn predict [person-name]
  (str "Greetings " person-name ". "
       (predictions (prediction-index person-name))))


